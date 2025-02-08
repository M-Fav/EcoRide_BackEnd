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

    public Integer createCovoiturage(CovoiturageRequestDTO covoiturageRequestDTO) {

        Covoiturage covoiturage = covoiturageRepository.save(CovoiturageMapper.INSTANCE.toCovoiturage(covoiturageRequestDTO));
        //On fait le save et on return l'id du covoiturage
        return covoiturage.getCovoiturageId();
    }


}
