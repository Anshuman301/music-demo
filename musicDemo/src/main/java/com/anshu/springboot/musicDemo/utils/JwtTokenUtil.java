package com.anshu.springboot.musicDemo.utils;

import java.util.Date;

import com.anshu.springboot.musicDemo.model.entity.User;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
    private static final String MY_SECRET = "myfirstSpring";
    public JwtTokenUtil() {}
    public String createToken(User user) {
        return Jwts.builder()
        .setSubject(user.getEmailAddress())
        .signWith(SignatureAlgorithm.HS512, MY_SECRET)
        .setIssuedAt(new Date())
        .compact();
    }

    public String getEmailAddress(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(MY_SECRET)
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims.getSubject());
        return claims.getSubject();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(MY_SECRET).parseClaimsJws(token).getBody();
            return true;
        }catch(Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }
}
