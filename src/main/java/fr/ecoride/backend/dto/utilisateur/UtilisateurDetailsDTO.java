package fr.ecoride.backend.dto.utilisateur;

import fr.ecoride.backend.enums.UserRoleEnum;
import fr.ecoride.backend.enums.UserStatutEnum;

public class UtilisateurDetailsDTO {

    private Integer utilisateurId;

    private String pseudo;

    private String nom;

    private String prenom;

    private UserRoleEnum role;

    private UserStatutEnum statut;

    public Integer getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Integer utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

    public UserStatutEnum getStatut() {
        return statut;
    }

    public void setStatut(UserStatutEnum statut) {
        this.statut = statut;
    }
}
