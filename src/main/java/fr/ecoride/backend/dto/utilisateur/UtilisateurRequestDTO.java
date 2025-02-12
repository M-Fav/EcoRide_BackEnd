package fr.ecoride.backend.dto.utilisateur;

import fr.ecoride.backend.enums.UserStatutEnum;

public class UtilisateurRequestDTO {

    private Integer utilisateurId;

    private UserStatutEnum statut;

    private String pseudo;

    public Integer getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Integer utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public UserStatutEnum getStatut() {
        return statut;
    }

    public void setStatut(UserStatutEnum statut) {
        this.statut = statut;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
