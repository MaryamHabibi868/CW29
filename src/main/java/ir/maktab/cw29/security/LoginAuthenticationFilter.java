package ir.maktab.cw29.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.maktab.cw29.domain.User;
import ir.maktab.cw29.dto.LoginDTO;
import ir.maktab.cw29.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;


@Component
public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final PathPatternRequestMatcher loginPath =
            PathPatternRequestMatcher.withDefaults().matcher(
                    HttpMethod.POST, "/users/login"
            );

    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;

    protected LoginAuthenticationFilter(AuthenticationManager authenticationManager,
                                        ObjectMapper objectMapper,
                                        JwtUtil jwtUtil) {
        super(loginPath, authenticationManager);
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed)
            throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {

        String token = jwtUtil.generateToken(User.builder()
                .username(authResult.getPrincipal().toString())
                .build());


        Map<String , String> responseBody = Map.of(
                "token" , token);

        objectMapper.writeValue(response.getWriter(), responseBody);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        LoginDTO loginDTO = objectMapper.readValue(
                request.getInputStream(), LoginDTO.class);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(), loginDTO.getPassword());

        return getAuthenticationManager()
                .authenticate(usernamePasswordAuthenticationToken);
    }
}
