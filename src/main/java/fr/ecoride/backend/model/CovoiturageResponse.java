package fr.ecoride.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CovoiturageResponse {

    @JsonProperty("lieuDepart")
    private String lieuDepart;

    @JsonProperty("lieuArrivee")
    private String lieuArrivee;

    @JsonProperty("nbPlaces")
    private int nbPlaces;


    public CovoiturageResponse(String lieuDepart, String lieuArrivee, int nbPlaces) {
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.nbPlaces = nbPlaces;
    }

    public String getLieuDepart() {
        return lieuDepart;
    }

    public String getLieuArrivee() {
        return lieuArrivee;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }
}
