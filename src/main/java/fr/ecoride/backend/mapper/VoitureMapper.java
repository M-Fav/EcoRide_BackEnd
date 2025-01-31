package fr.ecoride.backend.mapper;

import fr.ecoride.backend.dto.voiture.VoitureRequestDTO;
import fr.ecoride.backend.model.Voiture;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VoitureMapper {

    VoitureMapper INSTANCE = Mappers.getMapper(VoitureMapper.class);

    VoitureRequestDTO toVoitureRequestDTO(Voiture voiture);
    Voiture toVoiture(VoitureRequestDTO voitureRequestDTO);
}
