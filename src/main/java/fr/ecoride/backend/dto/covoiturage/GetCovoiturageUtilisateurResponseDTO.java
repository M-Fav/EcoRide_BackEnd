package fr.ecoride.backend.dto.covoiturage;

import java.util.List;

public class GetCovoiturageUtilisateurResponseDTO {

    List<CovoiturageResponseDTO> listeCovoituragesPasses;
    List<CovoiturageResponseDTO> listeCovoituragesEnCours;

    public List<CovoiturageResponseDTO> getListeCovoituragesPasses() {
        return listeCovoituragesPasses;
    }

    public void setListeCovoituragesPasses(List<CovoiturageResponseDTO> listeCovoituragesPasses) {
        this.listeCovoituragesPasses = listeCovoituragesPasses;
    }

    public List<CovoiturageResponseDTO> getListeCovoituragesEnCours() {
        return listeCovoituragesEnCours;
    }

    public void setListeCovoituragesEnCours(List<CovoiturageResponseDTO> listeCovoituragesEnCours) {
        this.listeCovoituragesEnCours = listeCovoituragesEnCours;
    }
}
