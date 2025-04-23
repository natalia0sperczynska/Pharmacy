package org.example.pharmacy.services.authService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import org.example.pharmacy.persistance.entities.AuthEntity;
import org.example.pharmacy.types.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService {
    @Value("${private.key}")
    private long TOKEN_VALIDITY_MILLIS = 1000 * 60 * 60;

    @Value("${token.signing.key}")
    private String jwtSigningKey;
    public String generateToken(AuthEntity userDetail){
        return generateToken(new HashMap<>(),userDetail);
    }

    public boolean isTokenValid(String token){
        try{
            return !isTokenExpired(token);
        } catch (Exception e){
            return false;
        }
    }
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    private boolean isTokenExpired(String token){
      return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
         return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(token).getPayload();
    }

    private String generateToken(Map<String, Object> extraClaims, AuthEntity userDetails){
        extraClaims.put("role", userDetails.getRole());
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY_MILLIS))
                .signWith(generateKey())
                .compact();
    }
    public Role extractRole(String token){
        String roleString = extractClaim(token,(claims)->claims.get("role",String.class));
        return Role.valueOf(roleString);
    }
    private SecretKey generateKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

//na labach:
//    private long TOKEN_VALIDITY_MILLIS=1000* 60 * 60;
//    private String SECRET = "secret";
//    public String generateToken(String username){
//        var now = System.currentTimeMillis();
//        var token = Jwts.builder()
//                .subject(username)//can be id
//                .claim("role","USER")
//                .issuedAt(new Date())
//                .expiration(new Date(now+TOKEN_VALIDITY_MILLIS))
//                .signWith(generateKey())
//                .compact();
//        return token;
//    }
//    private SecretKey generateKey() {
//        return Keys.hmacShakeyFor(SECRET.getBytes());
//    }
}
