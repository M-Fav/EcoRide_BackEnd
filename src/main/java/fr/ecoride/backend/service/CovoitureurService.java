package fr.ecoride.backend.service;

import fr.ecoride.backend.dto.covoitureur.CovoitureurRequestDTO;
import fr.ecoride.backend.mapper.CovoitureurMapper;
import fr.ecoride.backend.repository.CovoitureurRepository;
import org.springframework.stereotype.Service;

@Service
public class CovoitureurService {

    private final CovoitureurRepository covoitureurRepository;

    public CovoitureurService(CovoitureurRepository covoitureurRepository) {
        this.covoitureurRepository = covoitureurRepository;
    }

    public void createCovoitureur(CovoitureurRequestDTO covoitureurRequestDTO) {
        covoitureurRepository.save(CovoitureurMapper.INSTANCE.toCovoitureur(covoitureurRequestDTO));
    }

}
