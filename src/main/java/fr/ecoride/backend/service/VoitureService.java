package fr.ecoride.backend.service;

import fr.ecoride.backend.dto.voiture.VoitureRequestDTO;
import fr.ecoride.backend.dto.voiture.VoitureResponseDTO;
import fr.ecoride.backend.mapper.VoitureMapper;
import fr.ecoride.backend.model.Voiture;
import fr.ecoride.backend.repository.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoitureService {

    @Autowired
    VoitureRepository voitureRepository;

    public void createVoiture(VoitureRequestDTO voitureRequestDTO) {
        voitureRepository.save(VoitureMapper.INSTANCE.toVoiture(voitureRequestDTO));
    }

    public List<VoitureResponseDTO> getAllVoitures() {
        return voitureRepository.findAll()
                .stream()
                .map(voiture -> new VoitureResponseDTO(
                        voiture.getVoitureId(),
                        voiture.getModele(),
                        voiture.getCouleur(),
                        voiture.getEnergie(),
                        voiture.getMarque(),
                        voiture.getImmatriculation(),
                        voiture.getDatePremiereImmatriculation()))
                .collect(Collectors.toList());
    }
}
