package com.anshu.springboot.musicDemo.middleware;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anshu.springboot.musicDemo.db.UserRespo;
import com.anshu.springboot.musicDemo.model.entity.User;
import com.anshu.springboot.musicDemo.utils.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenFilter extends OncePerRequestFilter {
    @Value("${key.secret1}")
    private String SECRET;

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        System.out.println("Secret1" + SECRET);
        return new JwtTokenUtil(SECRET);
    }

    @Autowired
    private UserRespo userRespo;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException, ArrayIndexOutOfBoundsException {
        System.out.println("Hi::" + request.getRequestURI());
        final String header = request.getHeader("Authorization");
        if (!StringUtils.hasLength(header) || !header.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<>(Map.of("success", false))));
            return;
        }
        final String token = header.split(" ")[1].trim();
        if (!jwtTokenUtil().validateToken(token)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<>(Map.of("success", false))));
            return;
        }
        // Get user identity and set it on the spring security context
        User user = userRespo.findByEmailAddress(jwtTokenUtil().getEmailAddress(token));
        if(user == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(new ObjectMapper().writeValueAsString(new HashMap<>(Map.of("success", false))));
            return;
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user, null,
                null
        );
        authentication
                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return "/api/users/isSuccess".equals(request.getRequestURI()); 
    }
    
}
