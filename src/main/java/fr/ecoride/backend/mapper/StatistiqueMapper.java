package fr.ecoride.backend.mapper;

import fr.ecoride.backend.dto.statistique.StatistiqueResponseDTO;
import fr.ecoride.backend.model.Statistique;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StatistiqueMapper {

    StatistiqueMapper INSTANCE = Mappers.getMapper(StatistiqueMapper.class);

   List<StatistiqueResponseDTO> toStatistiqueResponseDTO(List<Statistique> listeStatistique);
}
