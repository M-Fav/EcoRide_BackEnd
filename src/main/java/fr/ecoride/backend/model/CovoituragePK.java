package fr.ecoride.backend.model;

import jakarta.persistence.*;

@Embeddable
public class CovoituragePK {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "covoiturage_id")
    private Integer covoiturageId;

    @Column(name = "utilisateur_id")
    private Integer utilisateurId;


    public Integer getCovoiturageId() {
        return covoiturageId;
    }

    public void setCovoiturageId(Integer covoiturageId) {
        this.covoiturageId = covoiturageId;
    }

    public Integer getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Integer utilisateurId) {
        this.utilisateurId = utilisateurId;
    }
}
