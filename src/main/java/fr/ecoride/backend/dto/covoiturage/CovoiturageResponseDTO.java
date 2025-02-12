package fr.ecoride.backend.dto.covoiturage;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class CovoiturageResponseDTO {

    private Integer covoiturageId;
    private LocalDate date;
    private LocalTime heureDepart;
    private LocalTime duree;
    private String lieuDepart;
    private String lieuArrivee;
    private String statut;
    private int nbPlace;
    private float prixPersonne;

    public CovoiturageResponseDTO(Integer covoiturageId, LocalDate date, LocalTime heureDepart, LocalTime duree, String lieuDepart, String lieuArrivee, String statut, int nbPlace, float prixPersonne) {
        this.covoiturageId = covoiturageId;
        this.date = date;
        this.heureDepart = heureDepart;
        this.duree = duree;
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.statut = statut;
        this.nbPlace = nbPlace;
        this.prixPersonne = prixPersonne;
    }

    public Integer getCovoiturageId() {
        return covoiturageId;
    }

    public void setCovoiturageId(Integer covoiturageId) {
        this.covoiturageId = covoiturageId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
