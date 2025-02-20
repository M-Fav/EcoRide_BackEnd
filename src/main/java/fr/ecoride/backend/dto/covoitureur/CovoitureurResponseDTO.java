package fr.ecoride.backend.dto.covoitureur;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.ecoride.backend.enums.CovoitureurRoleEnum;

public class CovoitureurResponseDTO {

    private CovoitureurRoleEnum role;
    private Integer utilisateurId;
    private Integer covoiturageId;
    private boolean validationCovoiturage;

    public CovoitureurResponseDTO(CovoitureurRoleEnum role, Integer utilisateurId, Integer covoiturageId, boolean validationCovoiturage) {
        this.role = role;
        this.utilisateurId = utilisateurId;
        this.covoiturageId = covoiturageId;
        this.validationCovoiturage = validationCovoiturage;
    }

    public CovoitureurRoleEnum getRole() {
        return role;
    }

    public void setRole(CovoitureurRoleEnum role) {
        this.role = role;
    }

    public Integer getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Integer utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public Integer getCovoiturageId() {
        return covoiturageId;
    }

    public void setCovoiturageId(Integer covoiturageId) {
        this.covoiturageId = covoiturageId;
    }

    public boolean getValidationCovoiturage() {
        return validationCovoiturage;
    }

    public void setValidationCovoiturage(boolean validationCovoiturage) {
        this.validationCovoiturage = validationCovoiturage;
    }
}
