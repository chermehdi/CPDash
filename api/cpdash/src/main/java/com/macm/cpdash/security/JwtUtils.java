package com.macm.cpdash.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * Utility class to handle operations regarding the JWT token
 * we use the email of the user to uniquely identify him
 */
public class JwtUtils {

    static final String CLAIM_KEY_USERNAME = "sub";                  // the subject of the token is going to the email
    static final String CLAIM_KEY_AUDIENCE = "audience";
    static final String CLAIM_KEY_CREATED = "created";

    private static final String AUDIENCE_UNKNOWN = "unknown";
    private static final String AUDIENCE_WEB = "web";
    private static final String AUDIENCE_MOBILE = "mobile";
    private static final String AUDIENCE_TABLET = "tablet";

    @Value("${jwt.secret}")
    private String secret;                                          // secret to sign the token with

    @Value("${jwt.expiration}")
    private Long expiration;                                        // number of seconds until expiration

    final Logger log = LoggerFactory.getLogger(JwtUtils.class);

    public String getEmailFromToken(String token) {
        String email = null;
        try {
            Claims claims = getClaimsFromToken(token);
            email = claims.getSubject();
        } catch (Exception e) {
        }
        return email;
    }

    public Claims getClaimsFromToken(String token) {
        Claims body = null;
        try {
            body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            body = null;
        }
        return body;
    }

    public Date getCreatedFromToken(String token) {
        Date ret = null;
        try {
            Claims claims = getClaimsFromToken(token);
            ret = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {

        }
        return ret;
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_AUDIENCE, "web");
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, email);
        claims.put(CLAIM_KEY_AUDIENCE, "web");
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public String getAudienceFromToken(String token) {
        String audience;
        try {
            final Claims claims = getClaimsFromToken(token);
            audience = (String) claims.get(CLAIM_KEY_AUDIENCE);
        } catch (Exception e) {
            audience = null;
        }
        return audience;
    }

    public String generateToken(Map<String, Object> claims) {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(generateExpirationDate())
                .compact();
        return token;
    }

    private Date generateExpirationDate() {
        return new Date((Long) System.currentTimeMillis() + expiration * 1000);
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        final String userName = getEmailFromToken(token);
        return user.getEmail().equals(userName) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDateFromToken(token);
        log.info("the token is " + token + " the expirationDate " + expirationDate + " now " + new Date());
        return expirationDate.before(new Date());
    }

}
