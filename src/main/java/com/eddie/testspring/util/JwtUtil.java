package com.eddie.testspring.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.eddie.testspring.common.Consts;
import com.eddie.testspring.config.JwtConfig;
import com.eddie.testspring.constant.Status;
import com.eddie.testspring.vo.UserPrincipal;
import io.jsonwebtoken.*;
import javafx.scene.chart.BarChart;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 3:13 下午
 */
@EnableConfigurationProperties(JwtConfig.class)
@Configuration
@Slf4j
public class JwtUtil {
    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private StringRedisTemplate redisTemplate;


    public String createJWT(Boolean rememberMe , Long id, String subject, List<String> roles, Collection<? extends GrantedAuthority> authorities){
        Date now = new Date();
        JwtBuilder builder = Jwts.builder()
                .setId(id.toString())
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256,jwtConfig.getKey())
                .claim("roles",roles)
                .claim("authorities",authorities);

        Integer ttl = rememberMe ? jwtConfig.getRemember():jwtConfig.getTtl();
        if(ttl>0){
            builder.setExpiration(DateUtil.offsetMillisecond(now,ttl.intValue()));

        }

        String jwt = builder.compact();

        redisTemplate.opsForValue().set(Consts.REDIS_JWT_KEY_PREFIX + subject,jwt,ttl, TimeUnit.MILLISECONDS);
        return jwt;
    }


    public String createJWT(Authentication authentication, Boolean rememberMe){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return createJWT(rememberMe,userPrincipal.getId(),userPrincipal.getUsername(),userPrincipal.getRoles(),userPrincipal.getAuthorities());

    }

    public Claims parseJWT(String jwt){
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtConfig.getKey()).parseClaimsJws(jwt).getBody();

            String username = claims.getSubject();
            String redisKey = Consts.REDIS_JWT_KEY_PREFIX + username;

            Long expire = redisTemplate.getExpire(redisKey,TimeUnit.MILLISECONDS);
            if(Objects.isNull(expire) || expire <= 0){
                throw new SecurityException(Status.TOKEN_EXPIRED.toString());

            }
            return claims;
        } catch (ExpiredJwtException e) {
            log.error("Token 已过期");
            throw new SecurityException(Status.TOKEN_EXPIRED.toString());
        } catch (UnsupportedJwtException e) {
            log.error("不支持的 Token");
            throw new SecurityException(Status.TOKEN_PARSE_ERROR.toString());
        } catch (MalformedJwtException e) {
            log.error("Token 无效");
            throw new SecurityException(Status.TOKEN_PARSE_ERROR.toString());
        } catch (SignatureException e) {
            log.error("无效的 Token 签名");
            throw new SecurityException(Status.TOKEN_PARSE_ERROR.toString());
        } catch (IllegalArgumentException e) {
            log.error("Token 参数不存在");
            throw new SecurityException(Status.TOKEN_PARSE_ERROR.toString());
        }
    }


    public void invalidateJWT(HttpServletRequest request){
        String jwt = getJwtFromRequest(request);
        String username = getUsernameFromJWT(jwt);
        redisTemplate.delete(Consts.REDIS_JWT_KEY_PREFIX +username);
    }

    public String getUsernameFromJWT(String jwt){
        Claims claims = parseJWT(jwt);
        return claims.getSubject();
    }

    public String getJwtFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(StrUtil.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer")){
            return bearerToken.substring(7);

        }
        return null;
    }



}
