package com.anshu.springboot.musicDemo.middleware;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anshu.springboot.musicDemo.utils.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class CsrfTokenFilter extends OncePerRequestFilter{

    @Value("${key.secret2}")
    private String SECRET;

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        System.out.println("secret2" + SECRET);
        return new JwtTokenUtil(SECRET);
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("music-token");
        if(!StringUtils.hasLength(token)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<>(Map.of("success", false))));
            return;
        }
        if(!jwtTokenUtil().validateCsrfToken(token)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<>(Map.of("success", false))));
            return;
        }
        filterChain.doFilter(request, response);
    }
    
    
}
