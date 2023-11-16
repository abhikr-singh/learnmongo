package com.example.learnmongo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.Objects;

@Component
public class ResponseHeaderFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestId = request.getHeader("x-requestId");
        if(Objects.nonNull(requestId)) {
            response.setHeader("x-responseId", requestId);
        }
        filterChain.doFilter(request, response);
    }
}
