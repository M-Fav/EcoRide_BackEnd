package fr.ecoride.backend.dto.avis;


public class AvisRequestDTO {

    private String commentaire;
    private String note;
    private boolean statut;
    private Integer covoitureurId;

    public AvisRequestDTO(String commentaire, String note, boolean statut, Integer covoitureurId) {
        this.commentaire = commentaire;
        this.note = note;
        this.statut = statut;
        this.covoitureurId = covoitureurId;
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

    public Integer getCovoitureurId() {
        return covoitureurId;
    }

    public void setCovoitureurId(Integer covoitureurId) {
        this.covoitureurId = covoitureurId;
    }
}
