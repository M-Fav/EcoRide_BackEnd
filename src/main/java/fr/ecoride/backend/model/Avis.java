package fr.ecoride.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "avis")
public class Avis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avis_id")
    private Integer avisId;
    @Column(name = "commentaire")
    private String commentaire;
    @Column(name = "note")
    private String note;
    @Column(name = "statut")
    private boolean statut;

    @Column(name = "covoitureur_id")
    private String covoitureurId;

    public Avis() {}

    public long getAvisId() {
        return avisId;
    }

    public void setAvisId(Integer avisId) {
        this.avisId = avisId;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean getStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public String getCovoitureurId() {
        return covoitureurId;
    }

    public void setCovoitureurId(String covoitureurId) {
        this.covoitureurId = covoitureurId;
    }
}
