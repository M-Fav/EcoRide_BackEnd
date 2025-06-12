package fr.ecoride.backend.controller;

import fr.ecoride.backend.model.AuthenticationResponse;
import fr.ecoride.backend.model.User;
import fr.ecoride.backend.service.AuthenticationService;
import fr.ecoride.backend.service.UserDetailsServiceImp;
import fr.ecoride.backend.utils.SanitizerUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authService;
    private UserDetailsServiceImp userDetailsServiceImp;
    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody User request
            ) {
        // Nettoyer les champs sensibles avant traitement
        request.setUsername(SanitizerUtil.strictSanitize(request.getUsername()));
        request.setEmail(SanitizerUtil.sanitizeHtml(request.getEmail()));

        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody User request
    ) {
        // Nettoyer le username avant authentification
        request.setUsername(SanitizerUtil.strictSanitize(request.getUsername()));
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/refresh_token")
    public ResponseEntity refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return authService.refreshToken(request, response);
    }
}
