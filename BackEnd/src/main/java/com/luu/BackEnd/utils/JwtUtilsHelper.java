package com.luu.BackEnd.utils;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtilsHelper {
    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long jwtExpirationInMs;
    private Key getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        return new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256");
    }

    // Lấy username từ JWT
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Lấy expiration date từ JWT
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Lấy bất kỳ claim nào từ JWT
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())  // Sử dụng Key đã được chuyển đổi
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Tạo token từ UserDetails
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    // Tạo token với subject và expiration
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Xác thực token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        return (username.equals(userDetails.getUsername()));
    }

//    public String generateToken(String data) {
//        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
//
//        // Thiết lập thời gian hết hạn cho token (1 giờ sau thời điểm hiện tại)
//        long expirationTimeInMs = 1000 * 60 * 60; // 1 giờ = 60 phút = 3600 giây = 3600 * 1000 ms
//        Date issuedAt = new Date();
//        Date expirationDate = new Date(issuedAt.getTime() + expirationTimeInMs);
//
//        // Chưa thiết lập Expiration Time cho token
//        return Jwts.builder().subject(data).signWith(key).compact();
//    }
//    public String getUserNameFromJwtToken(String token) {
//        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
//        return Jwts.parser().setSigningKey(key).build()
//                .parseClaimsJws(token).getBody().getSubject();
//    }
//    public boolean verifyToken(String token) {
//        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
//        try {
//            Jwts.parser()
//                    .setSigningKey(key)
//                    .build()
//                    .parseSignedClaims(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
}
