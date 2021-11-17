package com.eddie.testspring.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.eddie.testspring.constant.Status;
import com.eddie.testspring.service.serviceImpl.CustomUserDetailService;
import com.eddie.testspring.util.JwtUtil;
import com.eddie.testspring.util.ResponseUtil;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 2:34 下午
 */
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomConfig customConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (checkIgnore(request)) {
            filterChain.doFilter(request,response);
            return ;
        }

        String jwt = jwtUtil.getJwtFromRequest(request);

        if(StrUtil.isNotBlank(jwt)){
            try {
                String username = jwtUtil.getUsernameFromJWT(jwt);
                UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request,response);
            } catch (SecurityException e) {
                ResponseUtil.renderJson(response, Status.UNAUTHORIZED,null);
            }

        }else{
            ResponseUtil.renderJson(response, Status.UNAUTHORIZED,null);
        }
    }

    private boolean checkIgnore(HttpServletRequest request){
        String method = request.getMethod();

        HttpMethod httpMethod = HttpMethod.resolve(method);
        if (Objects.isNull(httpMethod)) {
            httpMethod = HttpMethod.GET;
        }

        Set<String> ignores = Sets.newHashSet();

        switch(httpMethod) {
            case GET:
                ignores.addAll(customConfig.getIgnores().getGet());
                break;
            case PUT:
                ignores.addAll(customConfig.getIgnores().getPut());
                break;
            case HEAD:
                ignores.addAll(customConfig.getIgnores().getHead());
                break;
            case POST:
                ignores.addAll(customConfig.getIgnores().getPost());
                break;
            case PATCH:
                ignores.addAll(customConfig.getIgnores().getPatch());
                break;
            case TRACE:
                ignores.addAll(customConfig.getIgnores().getTrace());
                break;
            case DELETE:
                ignores.addAll(customConfig.getIgnores().getDelete());
                break;
            case OPTIONS:
                ignores.addAll(customConfig.getIgnores().getOptions());
                break;
            default:
                break;
        }

        ignores.addAll(customConfig.getIgnores().getPattern());

        if(CollUtil.isNotEmpty(ignores)){
            for(String ignore: ignores){
                AntPathRequestMatcher matcher = new AntPathRequestMatcher(ignore,method);
                if(matcher.matches(request)){
                    return true;
                }
            }
        }
        return false;
    }

}
