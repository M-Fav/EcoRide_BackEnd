package fr.ecoride.backend.dto;

public class ErreurDTO {
    private int statut;
    private String typeErreur;
    private String erreur;

    public ErreurDTO(int statut, String typeErreur, String erreur) {
        this.statut = statut;
        this.typeErreur = typeErreur;
        this.erreur = erreur;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public String getTypeErreur() {
        return typeErreur;
    }

    public void setTypeErreur(String typeErreur) {
        this.typeErreur = typeErreur;
    }

    public String getErreur() {
        return erreur;
    }

    public void setErreur(String erreur) {
        this.erreur = erreur;
    }
}

