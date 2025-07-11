package ir.maktab.cw29.service;

import ir.maktab.cw29.repository.AuthorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailService implements UserDetailsService {

    private final AuthorService authorService;

    public MyUserDetailService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
       authorService.findByUsername(username)
    }
}
