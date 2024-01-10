package com.watcher.springboot.util;

import io.jsonwebtoken.*;

import java.util.UUID;

public class JwtUtil {
    private static String signature="Admin";
    public static String createToken(String name,String isVip){
        JwtBuilder jwtBuilder= Jwts.builder();
        String jwtToken=jwtBuilder
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                .claim("name",name)
                .claim("isVip",isVip)
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();
        return jwtToken;
    }
    public static Claims analyseToken(String token){
        JwtParser jwtParser=Jwts.parser();
        Jws<Claims> claimsJws=jwtParser.setSigningKey(signature).parseClaimsJws(token);
        Claims claims=claimsJws.getBody();
        System.out.println(claims.get("name"));
        System.out.println(claims.get("isVip"));
        return claims;
    }
}
