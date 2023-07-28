package com.example.demo;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.Principal;

//@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    Principal principal;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("AuthenticationFilter.doFilter...");
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        principal = httpRequest.getUserPrincipal();
        if (principal == null) {
            try {
				httpRequest.login("admin","admin");
                principal = httpRequest.getUserPrincipal();
                System.out.printf("AuthenticationFilter.doFilter -> Principal: %s%n", principal);

//                filterChain.doFilter(servletRequest, servletResponse);
			} catch (Exception e) {
				throw new SecurityException("Errore in fase di login: " + (e.getMessage() == null ? e.toString() : e.getMessage()), e);
			} finally {
                System.out.printf("AuthenticationFilter.doFilter -> Principal: %s%n", principal);
            }
        }

    }

    @Override
    public void destroy() {}
}
