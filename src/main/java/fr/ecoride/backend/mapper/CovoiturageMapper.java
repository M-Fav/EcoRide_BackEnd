package fr.ecoride.backend.mapper;

import fr.ecoride.backend.dto.covoiturage.CovoiturageRequestDTO;
import fr.ecoride.backend.dto.covoiturage.CovoiturageResponseDTO;
import fr.ecoride.backend.model.Covoiturage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CovoiturageMapper {

    CovoiturageMapper INSTANCE = Mappers.getMapper(CovoiturageMapper.class);

    CovoiturageResponseDTO toCovoiturageResponseDTO(Covoiturage covoiturage);

    Covoiturage toCovoiturage(CovoiturageRequestDTO covoiturageRequestDTO);

    List<CovoiturageResponseDTO> toListCovoiturageResponseDTO(List<Covoiturage> covoiturageList);

}
