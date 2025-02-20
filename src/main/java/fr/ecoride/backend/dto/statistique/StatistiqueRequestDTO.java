package fr.ecoride.backend.dto.statistique;


import fr.ecoride.backend.enums.AvisDecisionEnum;

public class StatistiqueRequestDTO {

    private Integer avisId;
    private String commentaire;
    private String note;
    private AvisDecisionEnum decision;
    private Integer covoitureurId;

    public StatistiqueRequestDTO(Integer avisId, String commentaire, String note, AvisDecisionEnum decision, Integer covoitureurId) {
        this.avisId = avisId;
        this.commentaire = commentaire;
        this.note = note;
        this.decision = decision;
        this.covoitureurId = covoitureurId;
    }

    public Integer getAvisId() {
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
