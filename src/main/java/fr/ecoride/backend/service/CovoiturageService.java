package fr.ecoride.backend.service;

import fr.ecoride.backend.controller.AuthenticationController;
import fr.ecoride.backend.dto.covoiturage.CovoiturageRequestDTO;
import fr.ecoride.backend.enums.CovoiturageStatutEnum;
import fr.ecoride.backend.mapper.CovoiturageMapper;
import fr.ecoride.backend.model.Covoiturage;
import fr.ecoride.backend.dto.covoiturage.CovoiturageResponseDTO;
import fr.ecoride.backend.repository.CovoiturageRepository;
import fr.ecoride.backend.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CovoiturageService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final CovoiturageRepository covoiturageRepository;

    private static String GET_COVOITURAGE = "getCovoiturage";
    private static String CREATE_COVOITURAGE = "createCovoiturage";
    private static String FIND_COVOITURAGES = "findCovoiturages";
    private static String DELETE_COVOITURAGE = "deleteCovoiturage";
    private static String UPDATE_STATUT_COVOITURAGE = "updateStatutCovoiturage";

    public CovoiturageService(CovoiturageRepository covoiturageRepository) {
        this.covoiturageRepository = covoiturageRepository;
    }

    /**
     * Permet de récupérer le liste des covoiturage pour un lieu de départ
     * et d'arrivée
     *
     * @param lieuDepart
     * @param lieuArrivee
     * @return
     */
    public List<CovoiturageResponseDTO> findCovoiturages(String lieuDepart, String lieuArrivee) {
        logger.debug(FIND_COVOITURAGES + Constantes.LOG_DEBUT);

        List <Covoiturage> listeCovoiturages = covoiturageRepository.findByLieuDepartAndLieuArrivee(lieuDepart, lieuArrivee);
        List<CovoiturageResponseDTO> listeCovoiturageResponseDTOS = CovoiturageMapper
                .INSTANCE
                .toListCovoiturageResponseDTO(listeCovoiturages);

        logger.debug(FIND_COVOITURAGES + Constantes.LOG_FIN);
        return listeCovoiturageResponseDTOS;
    }

    /**
     * Permet de créer un covoiturage
     *
     * @param covoiturageRequestDTO
     * @return
     */
    public Integer createCovoiturage(CovoiturageRequestDTO covoiturageRequestDTO) {
        logger.debug(CREATE_COVOITURAGE + Constantes.LOG_DEBUT);

        //On fait le save et on return l'id du covoiturage
        Covoiturage covoiturage = covoiturageRepository.save(CovoiturageMapper.INSTANCE.toCovoiturage(covoiturageRequestDTO));

        logger.debug(CREATE_COVOITURAGE + Constantes.LOG_FIN);
        return covoiturage.getCovoiturageId();
    }

    /**
     * Permet de récupérer un covoiturage
     *
     * @param covoiturageId
     * @return covoiturage
     */
    public Covoiturage getCovoiturage(Integer covoiturageId) {
        logger.debug(GET_COVOITURAGE + Constantes.LOG_DEBUT);

        //On récupère le covoiturage
        Covoiturage covoiturage = covoiturageRepository.findByCovoiturageId(covoiturageId);

        logger.debug(GET_COVOITURAGE + Constantes.LOG_FIN);
        return covoiturage;
    }

    /**
     * Permet de supprimer un covoiturage
     *
     * @param covoiturageId
     */
    public void deleteCovoiturage(Integer covoiturageId) {
        logger.debug(DELETE_COVOITURAGE + Constantes.LOG_DEBUT);

        //On supprime le covoiturage
        covoiturageRepository.deleteById(covoiturageId);

        logger.debug(DELETE_COVOITURAGE + Constantes.LOG_FIN);
    }

    /**
     * Permet de changer le statut du covoiturage
     * @param covoiturageId
     * @param statut
     */
    public void updateStatutCovoiturage(Integer covoiturageId, CovoiturageStatutEnum statut) {
        logger.debug(UPDATE_STATUT_COVOITURAGE + Constantes.LOG_DEBUT);

        Covoiturage covoiturage = covoiturageRepository.findByCovoiturageId(covoiturageId);

        covoiturage.setStatut(statut);
        covoiturageRepository.save(covoiturage);

        logger.debug(UPDATE_STATUT_COVOITURAGE + Constantes.LOG_FIN);
    }
}
