package fr.ecoride.backend.dto.covoiturage;

import java.time.LocalTime;

public class CovoiturageResponseDTO {

    private Integer covoiturageId;
    private String date;
    private LocalTime heureDepart;
    private String lieuDepart;
    private String lieuArrivee;
    private String statut;
    private int nbPlace;
    private float prixPersonne;
    private Integer conducteurId;
    private boolean validationCovoiturage;
    private Integer covoitureurId;

    public CovoiturageResponseDTO(Integer covoiturageId, String date, LocalTime heureDepart, String lieuDepart,
                                  String lieuArrivee, String statut, int nbPlace, float prixPersonne,
                                  Integer conducteurId, boolean validationCovoiturage, Integer covoitureurId) {
        this.covoiturageId = covoiturageId;
        this.date = date;
        this.heureDepart = heureDepart;
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.statut = statut;
        this.nbPlace = nbPlace;
        this.prixPersonne = prixPersonne;
        this.conducteurId = conducteurId;
        this.validationCovoiturage = validationCovoiturage;
        this.covoitureurId = covoitureurId;
    }

    public Integer getCovoiturageId() {
        return covoiturageId;
    }

    public void setCovoiturageId(Integer covoiturageId) {
        this.covoiturageId = covoiturageId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    public String getLieuDepart() {
        return lieuDepart;
    }

    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public String getLieuArrivee() {
        return lieuArrivee;
    }

    public void setLieuArrivee(String lieuArrivee) {
        this.lieuArrivee = lieuArrivee;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public float getPrixPersonne() {
        return prixPersonne;
    }

    public void setPrixPersonne(float prixPersonne) {
        this.prixPersonne = prixPersonne;
    }

    public Integer getConducteurId() {
        return conducteurId;
    }
    public void setConducteurId(Integer conducteurId) {
        this.conducteurId = conducteurId;
    }

    public boolean isValidationCovoiturage() {
        return validationCovoiturage;
    }

    public void setValidationCovoiturage(boolean validationCovoiturage) {
        this.validationCovoiturage = validationCovoiturage;
    }

    public Integer getCovoitureurId() {
        return covoitureurId;
    }

    public void setCovoitureurId(Integer covoitureurId) {
        this.covoitureurId = covoitureurId;
    }
}
