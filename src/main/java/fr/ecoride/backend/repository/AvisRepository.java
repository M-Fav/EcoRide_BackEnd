package fr.ecoride.backend.repository;

import fr.ecoride.backend.enums.AvisDecisionEnum;
import fr.ecoride.backend.model.Avis;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisRepository extends JpaRepository<Avis, Integer> {
    List<Avis> findByDecision(AvisDecisionEnum decision);
    Avis findByAvisId(Integer avisId);
}
