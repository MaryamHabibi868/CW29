package ir.maktab.cw29.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import ir.maktab.cw29.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
               .withClaim("authorities" , user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusMillis(jwtExpirationMs))
                .sign(Algorithm.HMAC256(jwtSecret));
    }

    public String getUsernameFromToken(String token) {
        DecodedJWT decodedJWT = decode(token);
        return decodedJWT.getSubject();
    }

    public boolean isValid(String token) {
        DecodedJWT decode = decode(token);
        return decode.getExpiresAt().after(Date.from(Instant.now()));
    }


    private DecodedJWT decode(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtSecret)).build();
        return verifier.verify(token);
    }

    public List<? extends GrantedAuthority> getAuthorities(String token) {
        DecodedJWT decodedJWT = decode(token);
        Claim authorities = decodedJWT.getClaim("authorities");
        List<String> stringAuthorities = authorities.asList(String.class);
        return stringAuthorities.stream().map(SimpleGrantedAuthority::new).toList();
    }
}