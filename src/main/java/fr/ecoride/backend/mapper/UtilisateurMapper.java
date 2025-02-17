package fr.ecoride.backend.mapper;

import fr.ecoride.backend.dto.utilisateur.UtilisateurDetailsDTO;
import fr.ecoride.backend.dto.utilisateur.UtilisateurRequestDTO;
import fr.ecoride.backend.dto.utilisateur.UtilisateurResponseDTO;
import fr.ecoride.backend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UtilisateurMapper {

    UtilisateurMapper INSANCE = Mappers.getMapper(UtilisateurMapper.class);

    UtilisateurResponseDTO toUtilsateurResponse(User user);

    User toUser(UtilisateurRequestDTO utilisateurRequestDTO);

    List<UtilisateurDetailsDTO> toUtilisateurDetailsDTO(List<User> users);
}
