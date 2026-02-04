package com.itheima.pojo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    /** 与测试类一致的秘钥 */
    private static final String SECRET = "MTE0NTE0Cg==";

    /** 12 小时，单位：毫秒 */
    private static final long EXPIRATION = 12 * 3600 * 1000;

    /**
     * 生成 JWT 令牌
     * @param claims 自定义数据
     * @return 令牌字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .compact();
    }

    /**
     * 解析 JWT 令牌
     * @param token 令牌
     * @return 载荷体 Claims
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    /** 可选：快速验证令牌是否有效 */
    public static boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
