package com.itlize.Korera;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class TestJwtToken {
    /**
     * Create jwt token through JJWT
     * @param args
     */
    public static void main(String[] args) {
        JwtBuilder builder = Jwts.builder().setId("12")
                .setSubject("Wenxuan Liu")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "MyKEY")
                .claim("username", "woshitc8535@gmail.com")
                .claim("password", "123456");

        JwtBuilder builder1 = Jwts.builder().setId("12")
                .setSubject("Wenxuan")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "MyKEY")
                .claim("username", "woshitc8535@gmail.com")
                .claim("password", "123456");

        System.out.println(builder.compact());
        System.out.println(builder1.compact());
    }
}
