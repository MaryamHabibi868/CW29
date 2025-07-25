package ir.maktab.cw29.security;

import ir.maktab.cw29.domain.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public JwtAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        User user = (User) userDetailsService
                .loadUserByUsername(authentication.getPrincipal().toString());

        if (user == null) {
            throw new UsernameNotFoundException("username not found");
        }
        if (passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
            authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            return authentication;
        }
        throw new BadCredentialsException("invalid password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
