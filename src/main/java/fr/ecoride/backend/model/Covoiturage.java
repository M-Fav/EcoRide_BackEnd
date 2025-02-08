package fr.ecoride.backend.model;

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
    @Column(name = "date")
    private Date date;
    @Column(name = "heure_depart")
    private Date heureDepart;
    @Column(name = "duree")
    private LocalTime duree;
    @Column(name = "lieu_depart")
    private String lieuDepart;
    @Column(name = "lieu_arrivee")
    private String lieuArrivee;
    @Column(name = "statut")
    private String statut;
    @Column(name = "nb_place")
    private int nbPlace;
    @Column(name = "prix_personne")
    private float prixPersonne;

    public Covoiturage() {}


    public Covoiturage(Integer covoiturageId, Date date, Date heureDepart, LocalTime duree, String lieuDepart, String lieuArrivee, String statut, int nbPlace, float prixPersonne) {
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

    public Date getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(Date heureDepart) {
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
}
