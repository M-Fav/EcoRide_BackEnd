package fr.ecoride.backend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "covoiturage")
public class Covoiturage {

    @EmbeddedId
    private CovoituragePK covoituragePK;
    @Column(name = "date_depart")
    private Date dateDepart;
    @Column(name = "heure_depart")
    private Date heureDepart;
    @Column(name = "lieu_depart")
    private String lieuDepart;
    @Column(name = "date_arrivee")
    private Date dateArrivee;
    @Column(name = "heure_arrivee")
    private Date heureArrivee;
    @Column(name = "lieu_arrivee")
    private String lieuArrivee;
    @Column(name = "statut")
    private String statut;
    @Column(name = "nb_place")
    private int nbPlace;
    @Column(name = "prix_personne")
    private float prixPersonne;

    public Covoiturage() {}

    public Covoiturage(Date dateDepart, Date heureDepart, String lieuDepart, Date dateArrivee, Date heureArrivee, String lieuArrivee, String statut, int nbPlace, float prixPersonne) {
        this.dateDepart = dateDepart;
        this.heureDepart = heureDepart;
        this.lieuDepart = lieuDepart;
        this.dateArrivee = dateArrivee;
        this.heureArrivee = heureArrivee;
        this.lieuArrivee = lieuArrivee;
        this.statut = statut;
        this.nbPlace = nbPlace;
        this.prixPersonne = prixPersonne;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
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

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public Date getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(Date heureArrivee) {
        this.heureArrivee = heureArrivee;
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
