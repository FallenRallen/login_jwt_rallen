package com.example.demo;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtUtil {
        private final String SECRET_KEY = "contraseña";

        public String generateToken(String username)
        {
            return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date(System.currentTimeMillis() + 1000 * 60 * 30))//res = 30 MINUTOS....
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
        }

        public String extractUsername(String token)
        {
            return Jwts.parser().setSigningKey(SECRET_KEY)
            .parseClaimsJws(token).getBody().getSubject();
        }

        public boolean validateToken (String Token){
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(Token);
            return true;

        }catch (JwtException | IllegalArgumentException e ){
            return false;
        }
    }
    
}

