package ir.maktab.cw29.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import ir.maktab.cw29.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private int jwtExpirationMs;


    // Generate JWT token
    public String generateToken(User user) {
       return JWT.create().withSubject(user.getUsername())
               .withClaim("authorities" , List.of(user.getAuthorities()))
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusMillis(jwtExpirationMs))
                .sign(Algorithm.HMAC256(jwtSecret));
    }

    // Get username from JWT token
    public String getUsernameFromToken(DecodedJWT decodedJWT) {
       return decodedJWT.getSubject();
    }

    public DecodedJWT validateToken(String token) {
        DecodedJWT decode = decode(token);
       if (decode.getExpiresAt().before(Date.from(Instant.now()))) {
           return null;
       }
       return decode;
    }


    // Validate JWT token
    public DecodedJWT decode(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtSecret)).build();
        return verifier.verify(token);
    }

    public List<GrantedAuthority> getAuthorities(DecodedJWT decodedJWT) {
        Claim authorities = decodedJWT.getClaim("authorities");
       return authorities.asList(GrantedAuthority.class);
    }
}