package fr.ecoride.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "statistique")
public class Statistique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statistique_id")
    private Integer avisId;
    @Column(name = "date")
    private String date;
    @Column(name = "type")
    private String type;
    @Column(name = "valeur")
    private String valeur;

    public Statistique() {}

    public long getAvisId() {
        return avisId;
    }

    public void setAvisId(Integer avisId) {
        this.avisId = avisId;
    }

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
