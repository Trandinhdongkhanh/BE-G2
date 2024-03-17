package com.hcmute.be_g2.config;

import com.hcmute.be_g2.entity.AppUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;


//Service used for normal authentication using username and password to authenticate which will generate a token
@Service
public class JwtService {
    //    private static final String SECRET_KEY = "Khanh24564!";
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final long EXPIRATION = 24 * 60 * 60 * 1000; // 24 hour
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtService.class);

    public String generateToken(AppUser appUser) {
        String username = appUser.getUsername();
        String email = appUser.getEmail();

        Date curDate = new Date();
        Date expiredDate = new Date(curDate.getTime() + EXPIRATION);

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("authorities", appUser.getAuthorities());

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer("self")
                .setSubject(username)
                .setIssuedAt(curDate)
                .setExpiration(expiredDate)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();

        return token;
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            LOGGER.error("JWT expired", ex.getMessage());
            ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
            ex.printStackTrace();
        } catch (MalformedJwtException ex) {
            LOGGER.error("JWT is invalid", ex);
            ex.printStackTrace();
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("JWT is not supported", ex);
            ex.printStackTrace();
        } catch (SignatureException ex) {
            LOGGER.error("Signature validation failed");
            ex.printStackTrace();
        }
        return false;
    }
}
