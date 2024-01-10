package com.watcher.springboot;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class SpringbootApplicationTests {

    @Test
    void contextLoads() {
    }
    private String signature="Admin";
    @Test
    void testJwt(){

        JwtBuilder jwtBuilder= Jwts.builder();
        String jwtToken=jwtBuilder
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                .claim("name","user1")
                .claim("isVip","0")
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();
        System.out.println(jwtToken);
        //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidXNlcjEiLCJwYXNzd29yZCI6IjEyMzQ1NiIsImlzVmlwIjoiMCIsImp0aSI6IjU5MGQyYzQ4LWQyZGQtNDM0MS1iMzQ2LTQ0NGNlZGFkZjFmMyJ9.e5amd_VPEPSPDqx0NL0_cH0YEtsTGJCzQctR39NHyHk

    }
    @Test
    void testAnalyse(){
        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidXNlcjEiLCJwYXNzd29yZCI6IjEyMzQ1NiIsImlzVmlwIjoiMCIsImp0aSI6IjU5MGQyYzQ4LWQyZGQtNDM0MS1iMzQ2LTQ0NGNlZGFkZjFmMyJ9.e5amd_VPEPSPDqx0NL0_cH0YEtsTGJCzQctR39NHyHk";
        JwtParser jwtParser=Jwts.parser();
        Jws<Claims> claimsJws=jwtParser.setSigningKey(signature).parseClaimsJws(token);
        Claims claims=claimsJws.getBody();
        System.out.println(claims.get("name"));
        System.out.println(claims.get("isVip"));
    }

}
