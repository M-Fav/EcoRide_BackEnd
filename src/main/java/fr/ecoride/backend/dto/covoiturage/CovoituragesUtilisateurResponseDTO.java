package fr.ecoride.backend.dto.covoiturage;

import fr.ecoride.backend.dto.utilisateur.UtilisateurDetailsDTO;

public class CovoituragesUtilisateurResponseDTO {

    private CovoiturageResponseDTO covoiturage;

    private UtilisateurDetailsDTO conducteur;


    public CovoiturageResponseDTO getCovoiturage() {
        return covoiturage;
    }

    public void setCovoiturage(CovoiturageResponseDTO covoiturage) {
        this.covoiturage = covoiturage;
    }

    public UtilisateurDetailsDTO getConducteur() {
        return conducteur;
    }

    public void setConducteur(UtilisateurDetailsDTO conducteur) {
        this.conducteur = conducteur;
    }
}
