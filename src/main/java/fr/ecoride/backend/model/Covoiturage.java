package fr.ecoride.backend.model;

import fr.ecoride.backend.enums.CovoiturageStatutEnum;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "covoiturage")
public class Covoiturage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "covoiturage_id")
    private Integer covoiturageId;

    @Column(name = "voiture_id")
    private Integer voitureId;

    @Column(name = "date")
    private Date date;
    @Column(name = "heure_depart")
    private LocalTime heureDepart;
    @Column(name = "duree")
    private LocalTime duree;
    @Column(name = "lieu_depart")
    private String lieuDepart;
    @Column(name = "lieu_arrivee")
    private String lieuArrivee;
    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private CovoiturageStatutEnum statut;
    @Column(name = "nb_place")
    private int nbPlace;
    @Column(name = "prix_personne")
    private float prixPersonne;

    public Covoiturage() {}


    public Integer getVoitureId() {
        return voitureId;
    }

    public void setVoitureId(Integer voitureId) {
        this.voitureId = voitureId;
    }

    public Integer getCovoiturageId() {
        return covoiturageId;
    }

    public void setCovoiturageId(Integer covoiturageId) {
        this.covoiturageId = covoiturageId;
    }

    public LocalTime getDuree() {
        return duree;
    }

    public void setDuree(LocalTime duree) {
        this.duree = duree;
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
