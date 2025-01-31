package fr.ecoride.backend.service;

import fr.ecoride.backend.dto.voiture.VoitureRequestDTO;
import fr.ecoride.backend.mapper.VoitureMapper;
import fr.ecoride.backend.repository.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoitureService {

    @Autowired
    VoitureRepository voitureRepository;

    public void createVoiture(VoitureRequestDTO voitureRequestDTO) {
        voitureRepository.save(VoitureMapper.INSTANCE.toVoiture(voitureRequestDTO));
    }
}
