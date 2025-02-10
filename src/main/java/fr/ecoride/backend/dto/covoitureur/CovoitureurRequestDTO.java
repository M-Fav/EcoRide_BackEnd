package fr.ecoride.backend.dto.covoitureur;

import fr.ecoride.backend.enums.CovoitureurRoleEnum;

public class CovoitureurRequestDTO {

    private Integer covoitureurId;

    private CovoitureurRoleEnum role;

    private Integer utilisateurId;

    private Integer covoiturageId;

    public CovoitureurRequestDTO(Integer covoitureurId, CovoitureurRoleEnum role, Integer utilisateurId, Integer covoiturageId) {
        this.covoitureurId = covoitureurId;
        this.role = role;
        this.utilisateurId = utilisateurId;
        this.covoiturageId = covoiturageId;
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
}
