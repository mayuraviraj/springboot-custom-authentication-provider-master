package com.example.demo.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class EverybodyAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        SecurityContextHolder.createEmptyContext();
        // THIS IS ONLY FOR TESTS. TO GET THIS WORK SENDING HARD CODED TOKEN. THIS SHOULD BE
        // CREATED FROM HEADER VALUES.
        customAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken("admin",
                "system"));
        filterChain.doFilter(request, response);
    }
}
