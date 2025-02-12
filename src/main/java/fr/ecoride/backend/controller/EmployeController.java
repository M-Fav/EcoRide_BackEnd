package fr.ecoride.backend.controller;

import fr.ecoride.backend.enums.UserRoleEnum;
import fr.ecoride.backend.model.AuthenticationResponse;
import fr.ecoride.backend.model.User;
import fr.ecoride.backend.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employe")
public class EmployeController {

    AuthenticationService authService;
    public EmployeController(AuthenticationService authService) {
        this.authService = authService;
    }


    @PostMapping("/createEmploye")
    private ResponseEntity<AuthenticationResponse> createEmploye(@RequestBody User user) {
        user.setRole(UserRoleEnum.EMPLOYE);
        return ResponseEntity.ok(authService.register(user));
    }
}
