package sportinterest.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sportinterest.security.config.JwtService;
import sportinterest.user.ERole;
import sportinterest.user.User;
import sportinterest.user.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /***
     * Create a new user if the mail doesn't already exist, save it to the database and return generated a token from the user
     * @param request
     * @return
     */
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .mail(request.getMail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(ERole.USER)
                .build();

        if (repository.findByMail(request.getMail()).isPresent()) {
            throw new RuntimeException("Mail already exists");
        }

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    /***
     * Check if the user exists and if the password is correct, then generate and return a token
     * @param request
     * @return
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getMail(), request.getPassword()));
        // If here, authentication was successful
        var user = repository.findByMail(request.getMail()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}
