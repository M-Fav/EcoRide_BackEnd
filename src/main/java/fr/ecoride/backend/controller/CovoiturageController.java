package fr.ecoride.backend.controller;

import fr.ecoride.backend.dto.covoiturage.CovoiturageRequestDTO;
import fr.ecoride.backend.dto.covoiturage.CovoiturageResponseDTO;
import fr.ecoride.backend.dto.covoitureur.CovoitureurRequestDTO;
import fr.ecoride.backend.enums.CovoitureurRoleEnum;
import fr.ecoride.backend.model.Covoiturage;
import fr.ecoride.backend.model.Covoitureur;
import fr.ecoride.backend.service.CovoiturageService;
import fr.ecoride.backend.service.CovoitureurService;
import fr.ecoride.backend.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/covoiturage")
public class CovoiturageController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private CovoiturageService covoiturageService;
    @Autowired
    private CovoitureurService covoitureurService;

    private static String DELETE_COVOITURAGE = "deleteCovoiturage";
    private static String FIND_COVOITURAGE = "findCovoiturages";
    private static String CREATE_COVOITURAGE = "createCovoiturage";

    @GetMapping("/covoiturages")
    public ResponseEntity findCovoiturages(@RequestBody Covoiturage request) {
        logger.debug(FIND_COVOITURAGE + Constantes.LOG_DEBUT);

        List<CovoiturageResponseDTO> listeCovoiturageResponseDTO =  covoiturageService.findCovoiturages(
                request.getLieuDepart(), request.getLieuArrivee());

        logger.debug(FIND_COVOITURAGE + Constantes.LOG_FIN);
        return ResponseEntity.ok(listeCovoiturageResponseDTO);
    }

    @PostMapping("/createCovoiturage")
    public void createCovoiturage(@RequestBody CovoiturageRequestDTO covoiturageRequestDTO) {
        logger.debug(CREATE_COVOITURAGE + Constantes.LOG_DEBUT);

        //On créer le covoiturage
        CovoitureurRequestDTO covoitureurRequestDTO = new CovoitureurRequestDTO(covoiturageRequestDTO.getCovoiturageId(), CovoitureurRoleEnum.CONDUCTEUR,
                covoiturageRequestDTO.getUtilisateurId(),
                covoiturageService.createCovoiturage(covoiturageRequestDTO));

        //On créer l'utilisateuir comme convoitureur CONDUCTEUR du covoiturage
        covoitureurService.createCovoitureur(covoitureurRequestDTO);

        logger.debug(CREATE_COVOITURAGE + Constantes.LOG_FIN);
    }

    @PostMapping("/deleteCovoiturage")
    public void deleteCovoiturage(@RequestBody CovoiturageRequestDTO covoiturageRequestDTO) {
        logger.debug(DELETE_COVOITURAGE + Constantes.LOG_DEBUT);

        //On récupère le covoiturage
        Covoiturage covoiturage = covoiturageService.getCovoiturage(covoiturageRequestDTO.getCovoiturageId());

        //On récupère les passagers du covoiturage
        List<Covoitureur> listeCovoitureur = covoitureurService.getCovoitureursOfCovoiturage(covoiturageRequestDTO.getCovoiturageId());

        //Si on a des covoitureurs liés au covoiturage on les supprimes
        for(Covoitureur covoitureur: listeCovoitureur){
            covoitureurService.deleteCovoitureur(covoiturage, covoitureur, covoiturage.getPrixPersonne());
        }

        //Suppression du covoiturage
        covoiturageService.deleteCovoiturage(covoiturageRequestDTO.getCovoiturageId());

        logger.debug(DELETE_COVOITURAGE + Constantes.LOG_FIN);
    }
}
