package pdp.uz.humo_online_jobs.security.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pdp.uz.humo_online_jobs.user.UserEntity;
import pdp.uz.humo_online_jobs.user.enums.UserType;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.token.expiration.mills}")
    private Long expiration;

    @Value("${jwt.token.secret.key}")
    private String secretKey;

    private SecretKey key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(UserEntity user) {
        UserType userType = user.getUserType(); // Get the UserType from UserEntity
        String roleName = userType != null ? userType.name() : ""; // Use the name of the enum

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .claim("username", user.getUsername())
                .claim("role", roleName)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            final Claims claims = parseClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
