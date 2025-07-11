package ir.maktab.cw29.dto;

import ir.maktab.cw29.domain.Author;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;

public class UserDetail implements UserDetails {

    @Getter
    private final Author author;

    public UserDetail(Author author) {
        this.author = author;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (author.get)
    }

    @Override
    public String getPassword() {
        return author.getPassword();
    }

    @Override
    public String getUsername() {
        return author.getUsername();
    }
}
