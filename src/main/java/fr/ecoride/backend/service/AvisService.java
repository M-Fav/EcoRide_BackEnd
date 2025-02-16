package fr.ecoride.backend.service;

import fr.ecoride.backend.dto.avis.AvisRequestDTO;
import fr.ecoride.backend.dto.avis.AvisResponseDTO;
import fr.ecoride.backend.enums.AvisDecisionEnum;
import fr.ecoride.backend.mapper.AvisMapper;
import fr.ecoride.backend.model.Avis;
import fr.ecoride.backend.repository.AvisRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AvisService {

    public final AvisRepository avisRepository;

    public AvisService(AvisRepository avisRepository) {
        this.avisRepository = avisRepository;
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
        return AvisMapper.INSTANCE.toListAvisResponseDTO(avisRepository.findByDecision(null));
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
