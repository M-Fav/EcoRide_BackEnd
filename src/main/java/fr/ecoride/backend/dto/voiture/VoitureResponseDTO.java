package fr.ecoride.backend.dto.voiture;

public class VoitureResponseDTO {

    private Integer user_id;
    private String modele;
    private String immatriculation;
    private String energie;
    private String couleur;
    private String datePremiereImmatriculation;
    private String marque;

    public VoitureResponseDTO(Integer user_id, String modele, String immatriculation, String energie, String couleur, String datePremiereImmatriculation, String marque) {
        this.user_id = user_id;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.energie = energie;
        this.couleur = couleur;
        this.datePremiereImmatriculation = datePremiereImmatriculation;
        this.marque = marque;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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
