package fr.ecoride.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "donnees_entreprise")
public class DonneesEntreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "valeur")
    private String valeur;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }
}
