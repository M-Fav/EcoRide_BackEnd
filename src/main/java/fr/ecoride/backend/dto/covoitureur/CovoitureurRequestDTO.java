package fr.ecoride.backend.dto.covoitureur;

import fr.ecoride.backend.enums.CovoitureurRoleEnum;

public class CovoitureurRequestDTO {

    private Integer covoitureurId;

    private CovoitureurRoleEnum role;

    private Integer utilisateurId;

    private Integer covoiturageId;

    private boolean validationCovoiturage;

    public CovoitureurRequestDTO(Integer covoitureurId, CovoitureurRoleEnum role, Integer utilisateurId, Integer covoiturageId, boolean validationCovoiturage) {
        this.covoitureurId = covoitureurId;
        this.role = role;
        this.utilisateurId = utilisateurId;
        this.covoiturageId = covoiturageId;
        this.validationCovoiturage = validationCovoiturage;
    }

    public Integer getCovoitureurId() {
        return covoitureurId;
    }

    public void setCovoitureurId(Integer covoitureurId) {
        this.covoitureurId = covoitureurId;
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

    public boolean isValidationCovoiturage() { return validationCovoiturage; }

    public void setValidationCovoiturage(boolean validationCovoiturage) { this.validationCovoiturage = validationCovoiturage; }
}
