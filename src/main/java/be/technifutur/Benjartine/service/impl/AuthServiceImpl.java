package be.technifutur.Benjartine.service.impl;

import be.technifutur.Benjartine.exception.FormValidationException;
import be.technifutur.Benjartine.jwt.JWTHolderDTO;
import be.technifutur.Benjartine.jwt.JwtProvider;
import be.technifutur.Benjartine.model.entity.User;
import be.technifutur.Benjartine.model.form.LoginForm;
import be.technifutur.Benjartine.model.form.RegistrationForm;
import be.technifutur.Benjartine.repository.BasketRepository;
import be.technifutur.Benjartine.repository.UserRepository;
import be.technifutur.Benjartine.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtProvider jwtProvider;
    private final BasketRepository basketRepository;

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder encoder,
            AuthenticationManager authManager,
            JwtProvider jwtProvider,
            BasketRepository basketRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtProvider = jwtProvider;
        this.basketRepository = basketRepository;
    }

    @Override
    public void register(RegistrationForm form) {

        if( userRepository.existsByUsername(form.getUsername()) )
            throw new FormValidationException("Nom d'utilisateur indisponible");

        User user = form.toEntity();

        user.setPassword( encoder.encode(user.getPassword()) );

        basketRepository.save( user.getBasket() );

        userRepository.save( user );

    }

    @Override
    public JWTHolderDTO login(LoginForm form) {

        Authentication auth = new UsernamePasswordAuthenticationToken(
                form.getUsername(),
                form.getPassword()
        );

        auth = authManager.authenticate( auth );

        String token = jwtProvider.createToken( auth );

        System.out.println("***********************************************");
        System.out.println(token);
        System.out.println("***********************************************");

        return new JWTHolderDTO( token );

    }
}
