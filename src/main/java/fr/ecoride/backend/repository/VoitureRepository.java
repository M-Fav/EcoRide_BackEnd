package fr.ecoride.backend.repository;

import fr.ecoride.backend.enums.AvisDecisionEnum;
import fr.ecoride.backend.model.Avis;
import fr.ecoride.backend.model.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Long> {

    List<Voiture> findByUtilisateurId(Integer utilisateurId);
}