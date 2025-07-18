package ir.maktab.cw29.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    public JwtAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        UserDetails userDetails = userDetailsService
                .loadUserByUsername(authentication.getPrincipal().toString());

        if (userDetails == null) {
            return null;
        }
        if (userDetails.getPassword().equals(authentication.getCredentials().toString())) {
            authentication.setAuthenticated(true);
            return authentication;
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // todo specification class
        return false;
    }
}
