package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTtest {
    @Test
    public void test() {
        Map<String, Object> dataMap=new HashMap<>();
        dataMap.put("username", "admin");
        dataMap.put("password", "admin");
        String compact = Jwts.builder().signWith(SignatureAlgorithm.HS256, "MTE0NTE0Cg==").addClaims(dataMap)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)).compact();
        //添加自定义信息
        System.out.println(compact);
    }
    @Test
    public void testParseJWT() {
        String token="eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6ImFkbWluIiwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc2OTk5NjM1M30.RwR_YZ_LtH0W54ZvVgJ_inwWIT14ucunZ0RvNVuNCQc";
        Claims claims = Jwts.parser().setSigningKey("MTE0NTE0Cg==").parseClaimsJws(token).getBody();
        System.out.println(claims);
    }
}
















