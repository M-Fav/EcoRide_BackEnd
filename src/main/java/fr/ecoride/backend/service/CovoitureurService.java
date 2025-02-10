package fr.ecoride.backend.service;

import fr.ecoride.backend.controller.AuthenticationController;
import fr.ecoride.backend.dto.covoitureur.CovoitureurRequestDTO;
import fr.ecoride.backend.email.EmailService;
import fr.ecoride.backend.enums.CovoitureurRoleEnum;
import fr.ecoride.backend.exception.CustomException;
import fr.ecoride.backend.mapper.CovoitureurMapper;
import fr.ecoride.backend.model.Covoiturage;
import fr.ecoride.backend.model.Covoitureur;
import fr.ecoride.backend.repository.CovoiturageRepository;
import fr.ecoride.backend.repository.CovoitureurRepository;
import fr.ecoride.backend.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CovoitureurService {
    private static final Logger logger = LoggerFactory.getLogger(CovoitureurService.class);

    private final CovoitureurRepository covoitureurRepository;
    private final CovoiturageRepository covoiturageRepository;
    private final UserDetailsServiceImp userDetailsServiceImp;
    private final EmailService emailService;

    private static String CREATE_COVOITUREUR = "createCovoitureur";
    private static String DELETE_COVOITUREUR = "deleteCovoitureur";
    private static String GET_COVOITUREURS_OF_COVOITURAGE = "getCovoitureursOfCovoiturage";
    private static String SEND_EMAIL_SUPPRESSION_COVOITURAGE = "sendEmailSuppressionCovoiturage";

    public CovoitureurService(CovoitureurRepository covoitureurRepository, CovoiturageRepository covoiturageRepository, UserDetailsServiceImp userDetailsServiceImp, EmailService emailService) {
        this.covoitureurRepository = covoitureurRepository;
        this.covoiturageRepository = covoiturageRepository;
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.emailService = emailService;
    }

    /**
     * Permet de créer un covoiturer
     *
     * @param covoitureurRequestDTO
     */
    public void createCovoitureur(CovoitureurRequestDTO covoitureurRequestDTO) {
        logger.debug(CREATE_COVOITUREUR + Constantes.LOG_DEBUT);

        //On créer le covoiturer
        covoitureurRepository.save(CovoitureurMapper.INSTANCE.toCovoitureur(covoitureurRequestDTO));

        //Si on créer un PASSAGER alors on soustrait le prix du covoiturage à ses crédits
        if(covoitureurRequestDTO.getRole().equals(CovoitureurRoleEnum.PASSAGER)){
            float prixCovoit = covoiturageRepository.findByCovoiturageId(covoitureurRequestDTO.getCovoiturageId()).getPrixPersonne();
            userDetailsServiceImp.updateCredits(covoitureurRequestDTO.getUtilisateurId(), prixCovoit, false);
        }

        logger.debug(CREATE_COVOITUREUR + Constantes.LOG_FIN);
    }

    /**
     * Permet de supprimer un covoitureur
     *
      * @param covoitureur
     * @param prixCovoiturage
     */
    public void deleteCovoitureur(Covoiturage covoiturage, Covoitureur covoitureur,float prixCovoiturage) {
        logger.debug(DELETE_COVOITUREUR + Constantes.LOG_DEBUT);

        //On supprime le covoitureur
        covoitureurRepository.deleteById(covoitureur.getCovoitureurId());

        //Si le covoitureur est un PASSAGER
        if(covoitureur.getRole().equals(CovoitureurRoleEnum.PASSAGER)){
            //On recrédite le prix du covoiturage
            userDetailsServiceImp.updateCredits(covoitureur.getUtilisateurId(), prixCovoiturage, true);
            //On envoie un mail pour informer l'utilisateur
            sendEmailSuppressionCovoiturage(covoiturage, covoitureur);
        }

        logger.debug(DELETE_COVOITUREUR + Constantes.LOG_FIN);
    }

    /**
     * Permet de récupérer la liste des covoitureur liés à un covoiturage
     *
     * @param covoiturageId
     * @return listeCovoitureur
     */
    public List<Covoitureur> getCovoitureursOfCovoiturage(Integer covoiturageId) {
        logger.debug(GET_COVOITUREURS_OF_COVOITURAGE + Constantes.LOG_DEBUT);

        //On récupère la list des covoitureurs d'un covoiturage
        List<Covoitureur> listeCovoitureur = covoitureurRepository.findByCovoiturageId(covoiturageId);

        logger.debug(GET_COVOITUREURS_OF_COVOITURAGE + Constantes.LOG_FIN);
        return listeCovoitureur;
    }

    /**
     * Permet d'envoyer un mail d'information
     * de l'annulation d'un covoiturage
     *
     * @param covoiturage
     */
    public void sendEmailSuppressionCovoiturage(Covoiturage covoiturage, Covoitureur covoitureur){
        logger.debug(SEND_EMAIL_SUPPRESSION_COVOITURAGE + Constantes.LOG_DEBUT);

        //Envoie du mail
        try {
            // Variables dynamiques à injecter dans le template
            Map<String, Object> variables = Map.of(
                    "villeDepart", covoiturage.getLieuDepart(),
                    "villeArrivee", covoiturage.getLieuArrivee(),
                    "dateCovoiturage", covoiturage.getDate().toString(),
                    "heureCovoiturage", covoiturage.getHeureDepart().toString()
            );

            // Appel à la méthode d'envoi d'email
            emailService.envoyerEmail("alexandren.blais@gmail.com",
                    "Annulation de votre covoiturage",
                    "mail_covoiturage_annule",
                    variables);

        } catch (Exception e) {
            throw new CustomException( "Erreur lors de l'envoi de l'email : " + e.getMessage(), HttpStatus.FORBIDDEN);
        }

        logger.debug(SEND_EMAIL_SUPPRESSION_COVOITURAGE + Constantes.LOG_FIN);
    }
}
