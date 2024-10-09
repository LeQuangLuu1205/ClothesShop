package com.luu.BackEnd.utils;

import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtilsHelper {
    @Value("${jwt.privateKey}")
    private String privateKey;
    public String generateToken(String data) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));

        // Thiết lập thời gian hết hạn cho token (1 giờ sau thời điểm hiện tại)
        long expirationTimeInMs = 1000 * 60 * 60; // 1 giờ = 60 phút = 3600 giây = 3600 * 1000 ms
        Date issuedAt = new Date();
        Date expirationDate = new Date(issuedAt.getTime() + expirationTimeInMs);

        // Chưa thiết lập Expiration Time cho token
        return Jwts.builder().subject(data).signWith(key).compact();
    }
    public boolean verifyToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
        try {
            Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
