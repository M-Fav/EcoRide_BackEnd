package fr.ecoride.backend.service;

import fr.ecoride.backend.dto.covoitureur.CovoitureurRequestDTO;
import fr.ecoride.backend.email.EmailService;
import fr.ecoride.backend.enums.CovoitureurRoleEnum;
import fr.ecoride.backend.mapper.CovoitureurMapper;
import fr.ecoride.backend.model.Covoiturage;
import fr.ecoride.backend.model.Covoitureur;
import fr.ecoride.backend.repository.CovoiturageRepository;
import fr.ecoride.backend.repository.CovoitureurRepository;
import fr.ecoride.backend.repository.UserRepository;
import fr.ecoride.backend.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CovoitureurService {
    private static final Logger logger = LoggerFactory.getLogger(CovoitureurService.class);

    private final CovoitureurRepository covoitureurRepository;
    private final CovoiturageRepository covoiturageRepository;
    private final UserDetailsServiceImp userDetailsServiceImp;
    private final UserRepository userRepository;
    private final EmailService emailService;

    private static String CREATE_COVOITUREUR = "createCovoitureur";
    private static String DELETE_COVOITUREUR = "deleteCovoitureur";
    private static String GET_COVOITUREURS_OF_COVOITURAGE = "getCovoitureursOfCovoiturage";
    private static String SEND_EMAIL_VALIDATION_COVOITUREUR = "sendEmailValidationCovoitureur";
    private static String VALIDATE_COVOITURAGE = "validateCovoiturage";
    private static String GET_COVOITUREURS_OF_UTILISATEUR = "getCovoitureursOfUtilisateur";

    public CovoitureurService(CovoitureurRepository covoitureurRepository, CovoiturageRepository covoiturageRepository, UserDetailsServiceImp userDetailsServiceImp, UserRepository userRepository, EmailService emailService) {
        this.covoitureurRepository = covoitureurRepository;
        this.covoiturageRepository = covoiturageRepository;
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    /**
     * Permet de créer un covoiturer
     *
     * @param covoitureurRequestDTO
     */
    @Transactional
    public void createCovoitureur(CovoitureurRequestDTO covoitureurRequestDTO) {
        logger.debug(CREATE_COVOITUREUR + Constantes.LOG_DEBUT);

        //Si on créer un PASSAGER :
        if(covoitureurRequestDTO.getRole().equals(CovoitureurRoleEnum.PASSAGER)){
            Covoiturage covoiturage = covoiturageRepository.findByCovoiturageId(covoitureurRequestDTO.getCovoiturageId());

            //On soustrait le prix du covoiturage à ses crédits
            userDetailsServiceImp.updateCredits(covoitureurRequestDTO.getUtilisateurId(), covoiturage.getPrixPersonne(), false);

            //On met à jour les place disponible
            covoiturage.setNbPlace(covoiturage.getNbPlace() - 1);
            covoiturageRepository.save(covoiturage);
        }

        //On créer le covoitureur
        covoitureurRepository.save(CovoitureurMapper.INSTANCE.toCovoitureur(covoitureurRequestDTO));

        logger.debug(CREATE_COVOITUREUR + Constantes.LOG_FIN);
    }

    /**
     * Permet de supprimer un covoitureur
     *
      * @param covoitureur
     * @param prixCovoiturage
     */
    @Transactional
    public void deleteCovoitureur(Covoiturage covoiturage, Covoitureur covoitureur,float prixCovoiturage) {
        logger.debug(DELETE_COVOITUREUR + Constantes.LOG_DEBUT);

        //On supprime le covoitureur
        covoitureurRepository.deleteById(covoitureur.getCovoitureurId());

        //Si le covoitureur est un PASSAGER
        if(covoitureur.getRole().equals(CovoitureurRoleEnum.PASSAGER)){
            //On recrédite le prix du covoiturage
            userDetailsServiceImp.updateCredits(covoitureur.getUtilisateurId(), prixCovoiturage, true);
            //On envoie un mail pour informer l'utilisateur
            String email = userRepository.findByUtilisateurId(covoitureur.getUtilisateurId()).getEmail();
            emailService.sendEmailSuppressionCovoiturage(covoiturage, covoitureur, email);
        }

        logger.debug(DELETE_COVOITUREUR + Constantes.LOG_FIN);
    }


    /**
     * Permet de récupérer la liste des covoitureur liés à un covoiturage
     *
     * @param covoiturageId
     * @return listeCovoitureur
     */
    @Transactional
    public List<Covoitureur> getCovoitureursOfCovoiturage(Integer covoiturageId) {
        logger.debug(GET_COVOITUREURS_OF_COVOITURAGE + Constantes.LOG_DEBUT);

        //On récupère la list des covoitureurs d'un covoiturage
        List<Covoitureur> listeCovoitureur = covoitureurRepository.findByCovoiturageId(covoiturageId);

        logger.debug(GET_COVOITUREURS_OF_COVOITURAGE + Constantes.LOG_FIN);
        return listeCovoitureur;
    }

    /**
     * Envoi du mail de validation du Covoiturage au Covoitureur
     *
     * @param covoiturage
     * @param covoitureur
     */
    @Transactional
    @Async
    public void sendEmailValidationCovoitureur(Covoiturage covoiturage,Covoitureur covoitureur) {
        logger.debug(SEND_EMAIL_VALIDATION_COVOITUREUR + Constantes.LOG_DEBUT);

        String email = userRepository.findByUtilisateurId(covoitureur.getUtilisateurId()).getEmail();
        emailService.sendEmailValidationCovoiturage(covoiturage, covoitureur, email);

        logger.debug(SEND_EMAIL_VALIDATION_COVOITUREUR + Constantes.LOG_FIN);
    }

    /**
     * Permet au covoitureur de valider le covoiturage
     *
     * @param covoiturageId
     * @param utilisateurId
     */
    @Transactional
    public void validateCovoiturage(Integer covoiturageId, Integer utilisateurId) {
        logger.debug(VALIDATE_COVOITURAGE + Constantes.LOG_DEBUT);

        //On récupère le covoitureur et on passe l'indicateur de validation covoiturage à true
        Covoitureur covoitureur = covoitureurRepository.findByUtilisateurIdAndCovoiturageId(utilisateurId,
                covoiturageId);
        covoitureur.setValidationCovoiturage(true);
        covoitureurRepository.save(covoitureur);

        logger.debug(VALIDATE_COVOITURAGE + Constantes.LOG_FIN);
    }

    /**
     * Permet de retourner l'indicateur de validation
     * totale d'un covoiturage par les covoitureurs
     *
     * @param listeCovoitureurs
     * @return
     */
    @Transactional
    public boolean isValidateByAllCovoitureurs(List<Covoitureur> listeCovoitureurs){

        //On vérifie si il reste un passager qui n'a pas validé le covoiturage
        boolean isNotAllValidate = listeCovoitureurs.stream()
                .anyMatch(c -> c.getRole() == CovoitureurRoleEnum.PASSAGER
                && !c.isValidationCovoiturage());

        return !isNotAllValidate;
    }

    /**
     * Permet de récupérer la liste des covoitureurs liés à
     * l'utilisateur
     *
     * @param utilisateurId
     * @return listeCovoitureur
     */
    @Transactional
    public List<Covoitureur> getCovoitureursOfUtilisateur(Integer utilisateurId) {
        logger.debug(GET_COVOITUREURS_OF_UTILISATEUR + Constantes.LOG_DEBUT);

        //On récupère la list des covoitureurs d'un utilisateur
        List<Covoitureur> listeCovoitureur = covoitureurRepository.findByUtilisateurId(utilisateurId);

        logger.debug(GET_COVOITUREURS_OF_UTILISATEUR + Constantes.LOG_FIN);
        return listeCovoitureur;
    }
}
