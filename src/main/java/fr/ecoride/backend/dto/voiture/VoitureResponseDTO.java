package fr.ecoride.backend.dto.voiture;

public class VoitureResponseDTO {

    private Integer voitureId;
    private String modele;
    private String immatriculation;
    private String energie;
    private String couleur;
    private String datePremiereImmatriculation;
    private String marque;

    public VoitureResponseDTO(Integer voitureId, String modele, String immatriculation, String energie, String couleur, String datePremiereImmatriculation, String marque) {
        this.voitureId = voitureId;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.energie = energie;
        this.couleur = couleur;
        this.datePremiereImmatriculation = datePremiereImmatriculation;
        this.marque = marque;
    }

    public Integer getVoitureId() {
        return voitureId;
    }

    public void setVoitureId(Integer voitureId) {
        this.voitureId = voitureId;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getEnergie() {
        return energie;
    }

    public void setEnergie(String energie) {
        this.energie = energie;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getDatePremiereImmatriculation() {
        return datePremiereImmatriculation;
    }

    public void setDatePremiereImmatriculation(String datePremiereImmatriculation) {
        this.datePremiereImmatriculation = datePremiereImmatriculation;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }
}
