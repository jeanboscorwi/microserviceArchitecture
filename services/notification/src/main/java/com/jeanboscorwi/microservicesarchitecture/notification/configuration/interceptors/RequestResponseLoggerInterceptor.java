package com.jeanboscorwi.microservicesarchitecture.notification.configuration.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class RequestResponseLoggerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //log the request
        MDC.put("url", request.getRequestURI());
        MDC.put("method", request.getMethod());
        MDC.put("startTime", String.valueOf(System.currentTimeMillis()));

        log.info(" => \"{} {}\"", MDC.get("method"), MDC.get("url"));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        var responseTime = 0L;
        var startTime = MDC.get("startTime");
        var endTime = System.currentTimeMillis();

        if(startTime != null){
            responseTime = endTime - Long.parseLong(startTime);
        }
        log.info("<= \"{} {}\" status: {} responseTime: {}",MDC.get("method"), MDC.get("url"), response.getStatus(), responseTime);
        MDC.clear();
    }
}
