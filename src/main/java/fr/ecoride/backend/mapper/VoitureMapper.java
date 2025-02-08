package fr.ecoride.backend.mapper;

import fr.ecoride.backend.dto.voiture.VoitureRequestDTO;
import fr.ecoride.backend.model.Voiture;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VoitureMapper {

    VoitureMapper INSTANCE = Mappers.getMapper(VoitureMapper.class);

    VoitureRequestDTO toVoitureRequestDTO(Voiture voiture);
    @Mapping(target = "utilisateurId", source = "utilisateurId")
    Voiture toVoiture(VoitureRequestDTO voitureRequestDTO);
}
