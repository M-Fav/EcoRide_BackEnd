package fr.ecoride.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.ecoride.backend.dto.utilisateur.UtilisateurResponseDTO;

public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("message")
    private String message;

    private UtilisateurResponseDTO utilisateur;

    public AuthenticationResponse(String accessToken, String refreshToken, String message, UtilisateurResponseDTO utilisateur) {
        this.accessToken = accessToken;
        this.message = message;
        this.refreshToken = refreshToken;
        this.utilisateur = utilisateur;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getMessage() {
        return message;
    }

    public UtilisateurResponseDTO getUtilisateur() {
        return utilisateur;
    }
}
