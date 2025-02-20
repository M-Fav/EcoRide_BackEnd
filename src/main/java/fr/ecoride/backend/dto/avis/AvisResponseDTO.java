package fr.ecoride.backend.dto.avis;

import fr.ecoride.backend.enums.AvisDecisionEnum;

public class AvisResponseDTO {

    private Integer avisId;
    private String commentaire;
    private String note;
    private AvisDecisionEnum decision;
    private Integer covoitureurId;
    private String nom;
    private String prenom;

    public AvisResponseDTO(Integer avisId, String commentaire, String note, AvisDecisionEnum decision, Integer covoitureurId, String nom, String prenom) {
        this.avisId = avisId;
        this.commentaire = commentaire;
        this.note = note;
        this.decision = decision;
        this.covoitureurId = covoitureurId;
        this.nom = nom;
        this.prenom = prenom;
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

    public Integer getAvisId() {
        return avisId;
    }

    public void setAvisId(Integer avisId) {
        this.avisId = avisId;
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
}
