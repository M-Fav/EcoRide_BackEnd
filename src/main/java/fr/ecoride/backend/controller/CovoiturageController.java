package fr.ecoride.backend.controller;

import fr.ecoride.backend.dto.covoiturage.CovoiturageRequestDTO;
import fr.ecoride.backend.dto.covoiturage.CovoiturageResponseDTO;
import fr.ecoride.backend.dto.covoiturage.CovoituragesUtilisateurResponseDTO;
import fr.ecoride.backend.dto.covoiturage.GetCovoiturageUtilisateurResponseDTO;
import fr.ecoride.backend.dto.covoitureur.CovoitureurRequestDTO;
import fr.ecoride.backend.dto.utilisateur.UtilisateurDetailsDTO;
import fr.ecoride.backend.email.EmailService;
import fr.ecoride.backend.enums.CovoiturageStatutEnum;
import fr.ecoride.backend.enums.CovoitureurRoleEnum;
import fr.ecoride.backend.mapper.CovoiturageMapper;
import fr.ecoride.backend.model.Covoiturage;
import fr.ecoride.backend.model.Covoitureur;
import fr.ecoride.backend.model.User;
import fr.ecoride.backend.service.CovoiturageService;
import fr.ecoride.backend.service.CovoitureurService;
import fr.ecoride.backend.service.UserDetailsServiceImp;
import fr.ecoride.backend.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/covoiturage")
public class CovoiturageController {

    private static final Logger logger = LoggerFactory.getLogger(CovoiturageController.class);

    @Autowired
    private CovoiturageService covoiturageService;
    @Autowired
    private CovoitureurService covoitureurService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    private static String DELETE_COVOITURAGE = "deleteCovoiturage";
    private static String FIND_COVOITURAGE = "findCovoiturages";
    private static String CREATE_COVOITURAGE = "createCovoiturage";
    private static String START_COVOITURAGE = "startCovoiturage";
    private static String TERMINATE_COVOITURAGE = "terminateCovoiturage";

    @GetMapping("/covoiturages")
    public ResponseEntity findCovoiturages(
            @RequestParam(defaultValue = "") String lieuDepart,
            @RequestParam(defaultValue = "") String lieuArrivee,
            @RequestParam(defaultValue = "") String date,
            @RequestParam(defaultValue = "") Integer utilisateurId) {
        logger.debug(FIND_COVOITURAGE + Constantes.LOG_DEBUT);

        List<CovoiturageResponseDTO> listeCovoiturageResponseDTO =  covoiturageService.findCovoiturages(
                utilisateurId, lieuDepart, lieuArrivee, date);

        logger.debug(FIND_COVOITURAGE + Constantes.LOG_FIN);
        return ResponseEntity.ok(listeCovoiturageResponseDTO);
    }

    //pour l'historique des covoiturages d'un utilisateur
    @GetMapping("/getCovoituragesUtilisateur")
    public ResponseEntity findUtilisateurCovoiturages(
            @RequestParam(defaultValue = "") Integer utilisateurId) {
        logger.debug(FIND_COVOITURAGE + Constantes.LOG_DEBUT);
        GetCovoiturageUtilisateurResponseDTO getCovoiturageUtilisateurResponseDTO = new GetCovoiturageUtilisateurResponseDTO();

        //On récupère la liste des covoitureurs liés à l'utilisateur
        List<Covoitureur> listeCovoitureur = covoitureurService.getCovoitureursOfUtilisateur(utilisateurId);

        //On récupère la liste des covoiturage liés aux covoitureurs
        List<CovoituragesUtilisateurResponseDTO> listeCovoituragesUtilisateurDTO = new ArrayList<CovoituragesUtilisateurResponseDTO>();

        for(Covoitureur covoitureur : listeCovoitureur){

            //Récupération du covoiturage
            Covoiturage covoiturage = covoiturageService.getCovoiturage(covoitureur.getCovoiturageId());
            CovoituragesUtilisateurResponseDTO covoituragesUtilisateurDTO = new CovoituragesUtilisateurResponseDTO();

            //Si le covoiturage existe
            if(covoiturage!=null){

                //Ajout du covoiturage à l'occurence en sortie
                covoituragesUtilisateurDTO.setCovoiturage(CovoiturageMapper.INSTANCE.toCovoiturageResponseDTO(covoiturage));

                User utilisateur = userDetailsServiceImp.getUser(covoiturage.getConducteurId());

                UtilisateurDetailsDTO utilisateurDetails = new UtilisateurDetailsDTO();
                utilisateurDetails.setNom(utilisateur.getNom());
                utilisateurDetails.setPrenom(utilisateur.getPrenom());
                utilisateurDetails.setPseudo(utilisateur.getPseudo());
                utilisateurDetails.setUtilisateurId(utilisateur.getUtilisateurId());

                covoituragesUtilisateurDTO.setConducteur(utilisateurDetails);
                Covoitureur covoitureurOfCovoiturage = covoitureurService.findCovoitureurOfUtilisateur(utilisateurId,covoiturage.getCovoiturageId());
                covoituragesUtilisateurDTO.getCovoiturage().setValidationCovoiturage(covoitureurOfCovoiturage.isValidationCovoiturage());
                covoituragesUtilisateurDTO.getCovoiturage().setCovoitureurId(covoitureurOfCovoiturage.getCovoitureurId());

                listeCovoituragesUtilisateurDTO.add(covoituragesUtilisateurDTO);
            }
        }

        //On veut faire deux listes de covoiturage selon le statut pour l'espace utilisateur
        // premier tri : ACTIF ou EN_COURS
        List<CovoituragesUtilisateurResponseDTO> listeCovoituragesEnCours = listeCovoituragesUtilisateurDTO.stream()
                .filter(d -> d.getCovoiturage().getStatut().equals("ACTIF") ||
                        d.getCovoiturage().getStatut().equals("EN_COURS") ||
                        d.getCovoiturage().getStatut().equals("TERMINE"))
                .toList();
        List<CovoiturageResponseDTO> covoiturageEnCoursResponseDTOList = new ArrayList<>();
        for (CovoituragesUtilisateurResponseDTO covoituragesUtilisateurResponseDTO : listeCovoituragesEnCours) {
            covoiturageEnCoursResponseDTOList.add(covoituragesUtilisateurResponseDTO.getCovoiturage());
        }

        //notre objet qui contiendra les deux listes
        getCovoiturageUtilisateurResponseDTO.setListeCovoituragesEnCours(covoiturageEnCoursResponseDTOList);

        // Deuxième tri : VALIDE
        List<CovoituragesUtilisateurResponseDTO> listeCovoituragesPasses = listeCovoituragesUtilisateurDTO.stream()
                .filter(d -> d.getCovoiturage().getStatut().equals("VALIDE"))
                .toList();
        List<CovoiturageResponseDTO> covoituragePasseResponseDTOList = new ArrayList<>();
        for (CovoituragesUtilisateurResponseDTO covoituragesUtilisateurResponseDTO : listeCovoituragesPasses) {
            covoituragePasseResponseDTOList.add(covoituragesUtilisateurResponseDTO.getCovoiturage());
        }

        getCovoiturageUtilisateurResponseDTO.setListeCovoituragesPasses(covoituragePasseResponseDTOList);

        logger.debug(FIND_COVOITURAGE + Constantes.LOG_FIN);
        return ResponseEntity.ok(getCovoiturageUtilisateurResponseDTO);
    }

    @PostMapping("/createCovoiturage")
    public void createCovoiturage(@RequestBody CovoiturageRequestDTO covoiturageRequestDTO) {
        logger.debug(CREATE_COVOITURAGE + Constantes.LOG_DEBUT);

        //On créer le covoiturage
        Integer covoiturageId = covoiturageService.createCovoiturage(covoiturageRequestDTO);
        CovoitureurRequestDTO covoitureurRequestDTO = new CovoitureurRequestDTO(covoiturageRequestDTO.getCovoiturageId(), CovoitureurRoleEnum.CONDUCTEUR,
                covoiturageRequestDTO.getUtilisateurId(), covoiturageId, true);

        //On créer l'utilisateur comme covoitureur CONDUCTEUR du covoiturage
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


    @PutMapping("/startCovoiturage")
    public void startCovoiturage(@RequestBody CovoiturageRequestDTO covoiturageRequestDTO) {
        logger.debug(START_COVOITURAGE + Constantes.LOG_DEBUT);

        //On appelle le service startCovoiturage pour démarrer le covoiturage
        covoiturageService.updateStatutCovoiturage(covoiturageRequestDTO.getCovoiturageId(),
                CovoiturageStatutEnum.EN_COURS);

        logger.debug(START_COVOITURAGE + Constantes.LOG_FIN);
    }

    @PutMapping("/terminateCovoiturage")
    public void terminateCovoiturage(@RequestBody CovoiturageRequestDTO covoiturageRequestDTO) {
        logger.debug(TERMINATE_COVOITURAGE + Constantes.LOG_DEBUT);

        //On passe le statut du covoiturage a terminé
        covoiturageService.updateStatutCovoiturage(covoiturageRequestDTO.getCovoiturageId(),
                CovoiturageStatutEnum.TERMINE);

        //On récupère les passagers du covoiturage
        List<Covoitureur> listeCovoitureur = covoitureurService.getCovoitureursOfCovoiturage(covoiturageRequestDTO.getCovoiturageId());

        //On récupère le covoiturage
        Covoiturage covoiturage = covoiturageService.getCovoiturage(covoiturageRequestDTO.getCovoiturageId());

        //On envoi le mail de validation de covoiturage aux covoitureur passagés
        for(Covoitureur covoitureur: listeCovoitureur) {
            if (covoitureur.getRole().equals(CovoitureurRoleEnum.PASSAGER)) {
                covoitureurService.sendEmailValidationCovoitureur(covoiturage, covoitureur);
            }
        }

        logger.debug(TERMINATE_COVOITURAGE + Constantes.LOG_FIN);
    }


}
