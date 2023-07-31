package com.example.demo;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(filterName = "LoggingFilter")
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("LoggingFilter.doFilter...");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        System.out.printf("LoggingFilter.doFilter -> %s%n", httpRequest.getUserPrincipal());
        chain.doFilter(request, response);
    }
}
