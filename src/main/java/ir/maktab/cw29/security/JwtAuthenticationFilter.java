package ir.maktab.cw29.security;

import ir.maktab.cw29.domain.User;
import ir.maktab.cw29.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        String token = null;
        if (authorization != null && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
        }
        if (Objects.nonNull(token) && jwtUtil.isValid(token)) {
            String usernameFromToken1 = jwtUtil.getUsernameFromToken(token);
            Long useId = jwtUtil.getUseId(token);
            UsernamePasswordAuthenticationToken userInfo = new UsernamePasswordAuthenticationToken(
                    User.builder().id(useId).username(usernameFromToken1).build(), null, jwtUtil.getAuthorities(token)
            );
            SecurityContextHolder.getContext().setAuthentication(userInfo);
        }
        filterChain.doFilter(request, response);
    }


}
