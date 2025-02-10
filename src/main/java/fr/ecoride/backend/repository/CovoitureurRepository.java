package fr.ecoride.backend.repository;

import fr.ecoride.backend.model.Covoitureur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CovoitureurRepository extends JpaRepository<Covoitureur, Integer> {
    List<Covoitureur> findByCovoiturageId(Integer covoiturageId);
}
