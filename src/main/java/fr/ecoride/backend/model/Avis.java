package fr.ecoride.backend.model;

import fr.ecoride.backend.enums.AvisDecisionEnum;
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
    @Enumerated(EnumType.STRING)
    @Column(name = "decision")
    private AvisDecisionEnum decision;
    @Column(name = "covoitureur_id")
    private Integer covoitureurId;

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

    public AvisDecisionEnum getDecision() {
        return decision;
    }

    public void setDecision(AvisDecisionEnum decision) {
        this.decision = decision;
    }

    public Integer getCovoitureurId() {
        return covoitureurId;
    }

    public void setCovoitureurId(Integer covoitureurId) {
        this.covoitureurId = covoitureurId;
    }
}
