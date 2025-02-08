package fr.ecoride.backend.service;

import fr.ecoride.backend.dto.covoiturage.CovoiturageRequestDTO;
import fr.ecoride.backend.mapper.CovoiturageMapper;
import fr.ecoride.backend.model.Covoiturage;
import fr.ecoride.backend.dto.covoiturage.CovoiturageResponseDTO;
import fr.ecoride.backend.repository.CovoiturageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CovoiturageService {

    private final CovoiturageRepository covoiturageRepository;

    public CovoiturageService(CovoiturageRepository covoiturageRepository) {
        this.covoiturageRepository = covoiturageRepository;
    }

    public List<CovoiturageResponseDTO> findCovoiturages(String lieuDepart, String lieuArrivee) {
        return CovoiturageMapper.INSTANCE.toListCovoiturageResponseDTO(covoiturageRepository.findByLieuDepartAndLieuArrivee(lieuDepart, lieuArrivee));
    }

    public void createCovoiturage(CovoiturageRequestDTO covoiturageRequestDTO) {

        covoiturageRepository.save(CovoiturageMapper.INSTANCE.toCovoiturage(covoiturageRequestDTO));
    }


}
