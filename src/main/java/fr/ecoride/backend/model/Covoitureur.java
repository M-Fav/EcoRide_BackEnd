package fr.ecoride.backend.model;

import fr.ecoride.backend.enums.CovoitureurRoleEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "covoitureur")
public class Covoitureur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "covoitureur_id")
    private Integer covoitureurId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private CovoitureurRoleEnum role;

    @Column(name = "validation_covoiturage")
    private boolean validationCovoiturage;

    @Column(name = "utilisateur_id")
    private Integer utilisateurId;

    @Column(name = "covoiturage_id")
    private Integer covoiturageId;

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

    public boolean isValidationCovoiturage() {
        return validationCovoiturage;
    }

    public void setValidationCovoiturage(boolean validationCovoiturage) {
        this.validationCovoiturage = validationCovoiturage;
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
