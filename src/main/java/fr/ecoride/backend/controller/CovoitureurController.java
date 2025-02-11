package fr.ecoride.backend.controller;

import fr.ecoride.backend.dto.covoitureur.CovoitureurRequestDTO;
import fr.ecoride.backend.enums.CovoiturageStatutEnum;
import fr.ecoride.backend.enums.CovoitureurRoleEnum;
import fr.ecoride.backend.model.Covoitureur;
import fr.ecoride.backend.service.CovoiturageService;
import fr.ecoride.backend.service.CovoitureurService;
import fr.ecoride.backend.service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/covoitureur")
public class CovoitureurController {

    @Autowired
    private CovoitureurService covoitureurService;
    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;
    @Autowired
    private CovoiturageService covoiturageService;


    @PostMapping("/createCovoitureur")
    public void createCovoitureur(@RequestBody CovoitureurRequestDTO covoitureurRequestDTO) {
        covoitureurService.createCovoitureur(covoitureurRequestDTO);
    }

    @PutMapping("/validateCovoiturage")
    public void validateCovoiturage(@RequestBody CovoitureurRequestDTO covoitureurRequestDTO) {
        //On valide le covoiturage pour le covoitureur
        covoitureurService.validateCovoiturage(covoitureurRequestDTO.getCovoiturageId(),
                covoitureurRequestDTO.getUtilisateurId());

        //On récupère la liste des covoitureurs
        List<Covoitureur> listeCovoitureurs = covoitureurService.getCovoitureursOfCovoiturage(covoitureurRequestDTO.getCovoiturageId());

        //Si tous les passager ont validés le covoiturage
        if(covoitureurService.isValidateByAllCovoitureurs(listeCovoitureurs)){
            //On récupère le nombre de passager du covoiturage
            long nombrePassager = listeCovoitureurs.stream()
                    .filter(c -> c.getRole() == CovoitureurRoleEnum.PASSAGER)
                    .count();

            //On ajoute les crédits au conducteur pour chaque passager
            if(nombrePassager > 0){
                Covoitureur covoitureurConducteur =  listeCovoitureurs.stream()
                        .filter(c -> c.getRole() == CovoitureurRoleEnum.CONDUCTEUR)
                        .findFirst()
                        .get();

                //On récupère le prix par personne pour le covoiturage
                float prixCovoiturage = covoiturageService.getCovoiturage(covoitureurRequestDTO.getCovoiturageId())
                        .getPrixPersonne();
                //On crédite le conducteur
                userDetailsServiceImp.updateCredits(covoitureurConducteur.getUtilisateurId(),
                        nombrePassager*prixCovoiturage, true);
            }

            //On passe le statu du covoiturage à VALIDE
            covoiturageService.updateStatutCovoiturage(covoitureurRequestDTO.getCovoiturageId(),
                    CovoiturageStatutEnum.VALIDE);
        }
    }
}
