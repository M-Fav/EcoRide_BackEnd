package fr.ecoride.backend.service;


import fr.ecoride.backend.controller.AuthenticationController;
import fr.ecoride.backend.dto.utilisateur.UtilisateurResponseDTO;
import fr.ecoride.backend.exception.CustomException;
import fr.ecoride.backend.mapper.UtilisateurMapper;
import fr.ecoride.backend.model.*;
import fr.ecoride.backend.repository.TokenRepository;
import fr.ecoride.backend.repository.UserRepository;
import fr.ecoride.backend.utils.Constantes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private static String REGISTER = "Register()";
    private static String LOGIN = "Login()";
    private static String REFRESH_TOKEN = "Refresh_token()";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 TokenRepository tokenRepository,
                                 AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public AuthenticationResponse register(User request) {
        logger.debug(REGISTER);

        // On controle si le User existe. s'il existe on l'authentifie
        if(userRepository.findByPseudo(request.getPseudo()).isPresent()) {
            return new AuthenticationResponse(null, null,"L'utilisateur existe déjà", null);
        }

        User user = request;
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCredit(Constantes.CREDIT_INSCRIPTION);
        userRepository.save(user);

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        saveUserToken(accessToken, refreshToken, user);

        UtilisateurResponseDTO utilisateurResponseDTO = UtilisateurMapper.INSANCE.toUtilsateurResponse(user);

        logger.debug(REGISTER);
        return new AuthenticationResponse(accessToken, refreshToken,"L'utilisateur a bien été enregistré", utilisateurResponseDTO);
    }

    @Transactional
    public AuthenticationResponse authenticate(User request) {
        logger.debug(LOGIN + Constantes.LOG_DEBUT);

        User user = userRepository.findByPseudo(request.getUsername())
                .orElseThrow(() -> {
                    logger.error("Utilisateur non trouvé avec le pseudo : {}", request.getUsername());
                    return new CustomException("Utilisateur non trouvé", HttpStatus.NOT_FOUND);
                });

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        revokeAllTokenByUser(user);
        saveUserToken(accessToken, refreshToken, user);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UtilisateurResponseDTO utilisateurResponseDTO =UtilisateurMapper.INSANCE.toUtilsateurResponse(user);

        logger.debug(LOGIN + Constantes.LOG_FIN);
        return new AuthenticationResponse(accessToken, refreshToken, "Connexion utilisateur réussie", utilisateurResponseDTO);
    }

    @Transactional
    private void revokeAllTokenByUser(User user) {
        List<Token> validTokens = tokenRepository.findByUtilisateurId(user.getUtilisateurId());
        if(validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t-> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }

    @Transactional
    private void saveUserToken(String accessToken, String refreshToken, User user) {
        Token token = new Token();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setLoggedOut(false);
        token.setUtilisateurId(user.getUtilisateurId());
        tokenRepository.save(token);
    }

    @Transactional
    public ResponseEntity refreshToken(HttpServletRequest request, HttpServletResponse response) {
        logger.debug(REFRESH_TOKEN);

        // On extrait le token
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        String token = authHeader.substring(7);

        // on extrait le token du user
        String username = jwtService.extractUsername(token);

        // on véirife si user existe dans la base de donnée
        User user = userRepository.findByPseudo(username)
                .orElseThrow(()->new RuntimeException("Pas d'utilisateur connecté"));

        // check si le token est valide
        if(jwtService.isValidRefreshToken(token, user)) {
            // génération token
            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            revokeAllTokenByUser(user);
            saveUserToken(accessToken, refreshToken, user);

            UtilisateurResponseDTO utilisateurResponseDTO = UtilisateurMapper.INSANCE.toUtilsateurResponse(user);

            return new ResponseEntity(new AuthenticationResponse(accessToken, refreshToken, "New token generated", utilisateurResponseDTO), HttpStatus.OK);
        }

        logger.debug(REFRESH_TOKEN);
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}
