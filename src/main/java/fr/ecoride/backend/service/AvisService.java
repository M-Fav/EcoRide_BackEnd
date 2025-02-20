package fr.ecoride.backend.service;

import fr.ecoride.backend.dto.avis.AvisRequestDTO;
import fr.ecoride.backend.dto.avis.AvisResponseDTO;
import fr.ecoride.backend.enums.AvisDecisionEnum;
import fr.ecoride.backend.mapper.AvisMapper;
import fr.ecoride.backend.model.Avis;
import fr.ecoride.backend.model.Covoitureur;
import fr.ecoride.backend.model.User;
import fr.ecoride.backend.repository.AvisRepository;
import fr.ecoride.backend.repository.CovoitureurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvisService {

    public final AvisRepository avisRepository;

   public final CovoitureurRepository covoitureurRepository;

   public final UserDetailsServiceImp userDetailsServiceImp;


    public AvisService(AvisRepository avisRepository, CovoitureurRepository covoitureurRepository, UserDetailsServiceImp userDetailsServiceImp) {
        this.avisRepository = avisRepository;
        this.covoitureurRepository = covoitureurRepository;
        this.userDetailsServiceImp = userDetailsServiceImp;
    }

    /**
     * Permet de creer un avis
     *
     * @param avisRequestDTO
     */
    @Transactional
    public void createAvis(AvisRequestDTO avisRequestDTO) {
        avisRepository.save(AvisMapper.INSTANCE.toAvis(avisRequestDTO));
    }

    /**
     * Permet de retourner une liste d'avis par
     * rapport à un statut
     * @return
     */
    @Transactional
    public List<AvisResponseDTO> getAvisATraiter() {
        List<AvisResponseDTO> avisResponseDTOList = new ArrayList<>();
        List<Avis> avisList = avisRepository.findByDecision(null);

        for (Avis avis : avisList) {
            Covoitureur covoitureur = covoitureurRepository.getReferenceById(avis.getCovoitureurId());
            User user = userDetailsServiceImp.getUser(covoitureur.getUtilisateurId());
            AvisResponseDTO avisResponseDTO = AvisMapper.INSTANCE.toAvisResponseDTO(avis);
            avisResponseDTO.setNom(user.getNom());
            avisResponseDTO.setPrenom(user.getPrenom());

            avisResponseDTOList.add(avisResponseDTO);
        }
        return avisResponseDTOList;
    }

    /**
     * Permet de traiter et valider un avis
     * @param avisId
     * @param decision
     */
    @Transactional
    public void traiterAvis(Integer avisId, AvisDecisionEnum decision) {
        Avis avisATraiter = avisRepository.findByAvisId(avisId);
        avisATraiter.setDecision(decision);
        avisRepository.save(avisATraiter);
    }

}
