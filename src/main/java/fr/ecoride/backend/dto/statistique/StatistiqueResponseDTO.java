package fr.ecoride.backend.dto.statistique;

import fr.ecoride.backend.enums.AvisDecisionEnum;

public class StatistiqueResponseDTO {

    private String date;
    private String type;
    private String valeur;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }
}
