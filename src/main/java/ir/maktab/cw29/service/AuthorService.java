package ir.maktab.cw29.service;

import ir.maktab.cw29.domain.Author;
import ir.maktab.cw29.dto.AuthorResponseDTO;
import ir.maktab.cw29.dto.RegisterUpdateAuthor;
import ir.maktab.cw29.mapper.AuthorMapper;
import ir.maktab.cw29.repository.AuthorRepository;
import ir.maktab.cw29.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService extends UserService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthorService(UserRepository userRepository,
                         PasswordEncoder passwordEncoder,
                         AuthorRepository authorRepository,
                         AuthorMapper authorMapper) {
        super(userRepository, passwordEncoder);
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public AuthorResponseDTO registrationAuthor(RegisterUpdateAuthor registerUpdateAuthor) {

        Author author = authorMapper.mapTpEntity(registerUpdateAuthor);
        author.setPassword(
                passwordEncoder.encode(registerUpdateAuthor.getPassword())
        );

        return authorMapper.mapToDTO(authorRepository.save(author));
    }

}
