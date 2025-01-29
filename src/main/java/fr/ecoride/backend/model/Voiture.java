package fr.ecoride.backend.model;

import jakarta.persistence.*;

@Entity
public class Voiture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voiture_id")
    private long voitureId;
    @Column(name = "modele")
    private String modele;
    @Column(name = "immatriculation")
    private String immatriculation;
    @Column(name = "energie")
    private String energie;
    @Column(name = "couleur")
    private String couleur;
    @Column(name = "date_premiere_immatriculation")
    private String datePremiereImmatriculation;
    @Column(name = "marque")
    private String marque;

    public Voiture() {}

    public Voiture(String modele, String immatriculation, String energie, String couleur, String datePremiereImmatriculation) {
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.energie = energie;
        this.couleur = couleur;
        this.datePremiereImmatriculation = datePremiereImmatriculation;
    }

    public long getVoitureId() {
        return voitureId;
    }

    public void setVoitureId(long voitureId) {
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
