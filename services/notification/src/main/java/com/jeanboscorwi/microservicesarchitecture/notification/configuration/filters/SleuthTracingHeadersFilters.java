package com.jeanboscorwi.microservicesarchitecture.notification.configuration.filters;

import lombok.AllArgsConstructor;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@Component
public class SleuthTracingHeadersFilters extends OncePerRequestFilter {

    private final Tracer tracer;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(ObjectUtils.isEmpty(response.getHeader("X-B3-SpanId"))){
            Span currentSpan = this.tracer.currentSpan();
            if(currentSpan != null){
                response.setHeader("X-B3-SpanId", currentSpan.context().spanId());
                response.setHeader("X-B3-TraceId", currentSpan.context().traceId());
            }
        }
        filterChain.doFilter(request, response);
    }
}
