package fr.ecoride.backend.dto.covoiturage;

import java.time.LocalTime;
import java.util.Date;

public class CovoiturageRequestDTO {

    private Integer covoiturageId;
    private Integer voitureId;
    private Integer utilisateurId;
    private Date date;
    private LocalTime heureDepart;
    private LocalTime duree;
    private String lieuDepart;
    private String lieuArrivee;
    private String statut;
    private int nbPlace;
    private float prixPersonne;

    public CovoiturageRequestDTO(Integer covoiturageId, Integer voitureId, Integer utilisateurId, Date date, LocalTime heureDepart, LocalTime duree, String lieuDepart, String lieuArrivee, String statut, int nbPlace, float prixPersonne) {
        this.covoiturageId = covoiturageId;
        this.voitureId = voitureId;
        this.utilisateurId = utilisateurId;
        this.date = date;
        this.heureDepart = heureDepart;
        this.duree = duree;
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.statut = statut;
        this.nbPlace = nbPlace;
        this.prixPersonne = prixPersonne;
    }

    public Integer getCovoiturageId() { return covoiturageId; }

    public void setCovoiturageId(Integer covoiturageId) { this.covoiturageId = covoiturageId; }

    public Integer getVoitureId() {
        return voitureId;
    }

    public void setVoitureId(Integer voitureId) {
        this.voitureId = voitureId;
    }

    public Integer getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Integer utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    public LocalTime getDuree() {
        return duree;
    }

    public void setDuree(LocalTime duree) {
        this.duree = duree;
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
}
