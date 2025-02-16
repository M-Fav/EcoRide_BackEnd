package fr.ecoride.backend.dto.covoiturage;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.ecoride.backend.enums.CovoiturageStatutEnum;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class CovoiturageRequestDTO {

    private Integer covoiturageId;
    private Integer voitureId;
    private Integer utilisateurId;
    private String date;
    private LocalTime heureDepart;
    private String lieuDepart;
    private String lieuArrivee;
    private CovoiturageStatutEnum statut;
    private int nbPlace;
    private float prixPersonne;

    public CovoiturageRequestDTO(Integer covoiturageId, Integer voitureId, Integer utilisateurId, String date, LocalTime heureDepart, String lieuDepart, String lieuArrivee, CovoiturageStatutEnum statut, int nbPlace, float prixPersonne) {
        this.covoiturageId = covoiturageId;
        this.voitureId = voitureId;
        this.utilisateurId = utilisateurId;
        this.date = date;
        this.heureDepart = heureDepart;
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

    public CovoiturageStatutEnum getStatut() {
        return statut;
    }

    public void setStatut(CovoiturageStatutEnum statut) {
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
