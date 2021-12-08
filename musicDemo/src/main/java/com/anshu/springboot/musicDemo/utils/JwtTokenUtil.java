package com.anshu.springboot.musicDemo.utils;

import java.util.Date;

import com.anshu.springboot.musicDemo.model.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil {
    private String secret;
    
    public JwtTokenUtil(String secret) {
        this.secret = secret;
    }

    public String createToken(User user) {
        return Jwts.builder()
        .setSubject(user.getEmailAddress())
        .signWith(SignatureAlgorithm.HS256, secret)
        .setIssuedAt(new Date())
        .compact();
    }

    public String getEmailAddress(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        System.out.println(claims.getSubject());
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        System.out.println("Secret1" + secret);
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return true;
        }catch(Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

    public boolean validateCsrfToken(String token) {
        System.out.println("Secret2" + secret);
        try {
            Claims claims = Jwts.parser().parseClaimsJwt(token).getBody();
            if(new Date().getTime() > claims.getExpiration().getTime())
                return false;
            return true;
        }catch(Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }
}
