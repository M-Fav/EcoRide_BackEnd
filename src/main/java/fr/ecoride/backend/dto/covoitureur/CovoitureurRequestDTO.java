package fr.ecoride.backend.dto.covoitureur;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.ecoride.backend.enums.CovoitureurRoleEnum;

public class CovoitureurRequestDTO {

    @JsonProperty("role")
    private CovoitureurRoleEnum role;

    @JsonProperty("utilisateur_id")
    private Integer utilisateurId;

    @JsonProperty("covoiturage_id")
    private Integer covoiturageId;

    public CovoitureurRequestDTO(CovoitureurRoleEnum role, Integer utilisateurId, Integer covoiturageId) {
        this.role = role;
        this.utilisateurId = utilisateurId;
        this.covoiturageId = covoiturageId;
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
