package com.eddie.testspring.aspectj;

import cn.hutool.core.util.ArrayUtil;
import eu.bitwalker.useragentutils.UserAgent;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.google.common.collect.Maps;

import javax.print.attribute.standard.RequestingUserName;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/15 12:26 下午
 */
@Aspect
@Component
@Slf4j
public class AopLog {

    public static final String UNKNOWN = "unknown";

   @Pointcut("execution(public * com.eddie.testspring.bean.*Controller.*(..))")
    public void log(){}

    @Around("log()")
    public void arroudLog(ProceedingJoinPoint point) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        long startTime = System.currentTimeMillis();
        Object result = point.proceed();
        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);

        final Log l  = Log.builder()
                .threadId(Long.toString(Thread.currentThread().getId()))
                .threadName(Thread.currentThread().getName())
                .ip(getIp(request))
                .url(request.getRequestURL().toString())
                .classMethod(String.format("%s.%s",point.getSignature().getDeclaringTypeName(),point.getSignature().getName()))
                .httpMethod(request.getMethod())
                .requestParams(getNameAndValue(point))
                .result(result)
                .timeCost(System.currentTimeMillis() - startTime)
                .userAgent(header)
                .browser(userAgent.getBrowser().toString())
                .os(userAgent.getOperatingSystem().toString())
                .build();



        log.info("Request Log Info : {}", JSONUtil.toJsonStr(l));
    }


    private Map<String,Object> getNameAndValue(ProceedingJoinPoint joinPoint){
        final Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        final Object[] args = joinPoint.getArgs();
        final String[] names = methodSignature.getParameterNames();
        if(ArrayUtil.isEmpty(names) || ArrayUtil.isEmpty(args)){
            return Collections.emptyMap();
        }
        if(names.length!=args.length){
            log.warn("{}方法参数名和参数值的数量不一致", methodSignature.getName());
            return Collections.emptyMap();
        }
        Map<String,Object> map = Maps.newHashMap();
        for (int i = 0 ; i < names.length ; i++){
            map.put(names[i] ,args[i]);

        }
        return map;
    }


    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-Ip");

        }
        if(ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-Ip");

        }
        if(ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }

        String comma = ",";
        String localhost = "127.0.0.1";
        if(ip.contains(comma)){
            ip = ip.split(",")[0];
        }
        if(localhost.equals(ip)){
            try{
                ip = InetAddress.getLocalHost().getHostAddress();

            }catch (UnknownHostException e){
                log.error(e.getMessage(),e);
            }
        }
        return ip;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static class Log{
        private String threadId;

        private String threadName;

        private String ip;

        private String url;

        private String httpMethod;

        private String classMethod;

        private Object requestParams;

        private Object result;

        private Long timeCost;

        private String os;

        private String browser;

        private String userAgent;
    }
}
