package com.itlize.Korera;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class TestParseToken {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMiIsInN1YiI6Ildlbnh1YW4gTGl1IiwiaWF0IjoxNTg1MTUyODEyLCJ1c2VybmFtZSI6Indvc2hpdGM4NTM1QGdtYWlsLmNvbSIsInBhc3N3b3JkIjoiMTIzNDU2In0.bqF5OP-r5NPG7leJF-S0MfNbrYZJn_NbbjfE4MGKqJE";
        Claims claims = Jwts.parser().setSigningKey("MyKEY").parseClaimsJws(token).getBody();

        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());

        System.out.println(claims.get("username"));
        System.out.println(claims.get("password"));

    }
}
