package fr.ecoride.backend.service;

import fr.ecoride.backend.dto.avis.AvisRequestDTO;
import fr.ecoride.backend.dto.avis.AvisResponseDTO;
import fr.ecoride.backend.mapper.AvisMapper;
import fr.ecoride.backend.repository.AvisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvisService {

    public final AvisRepository avisRepository;

    public AvisService(AvisRepository avisRepository) {
        this.avisRepository = avisRepository;
    }

    public void createAvis(AvisRequestDTO avisRequestDTO) {
        avisRepository.save(AvisMapper.INSTANCE.toAvis(avisRequestDTO));
    }

    //Trouver les avis en statut false pour l'employé
    public List<AvisResponseDTO> findAvisByStatut(boolean statut) {
        return AvisMapper.INSTANCE.toListAvisResponseDTO(avisRepository.findAvisByStatut(statut));

    }
}
