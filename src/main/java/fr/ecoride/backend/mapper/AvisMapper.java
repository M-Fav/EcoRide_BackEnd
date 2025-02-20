package fr.ecoride.backend.mapper;

import fr.ecoride.backend.dto.avis.AvisRequestDTO;
import fr.ecoride.backend.dto.avis.AvisResponseDTO;
import fr.ecoride.backend.model.Avis;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AvisMapper {

    AvisMapper INSTANCE = Mappers.getMapper(AvisMapper.class);

    Avis toAvis(AvisRequestDTO avisRequestDTO);

    List<AvisResponseDTO> toListAvisResponseDTO(List<Avis> avisList);

    AvisResponseDTO toAvisResponseDTO(Avis avis);
}
