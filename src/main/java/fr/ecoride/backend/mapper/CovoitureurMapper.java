package fr.ecoride.backend.mapper;

import fr.ecoride.backend.dto.covoitureur.CovoitureurRequestDTO;
import fr.ecoride.backend.dto.covoitureur.CovoitureurResponseDTO;
import fr.ecoride.backend.model.Covoitureur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CovoitureurMapper {

    CovoitureurMapper INSTANCE = Mappers.getMapper(CovoitureurMapper.class);

    CovoitureurResponseDTO toCovoitureurResponse(Covoitureur covoitureur);

    Covoitureur toCovoitureur(CovoitureurRequestDTO covoitureurRequestDTO);
}
