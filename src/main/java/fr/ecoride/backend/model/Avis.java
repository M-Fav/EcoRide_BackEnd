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
    private String statut;

    public Avis() {}

    public Avis(String commentaire, String note, String statut) {
        this.commentaire = commentaire;
        this.note = note;
        this.statut = statut;
    }

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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
