package fr.ecoride.backend.repository;

import fr.ecoride.backend.model.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisRepository extends JpaRepository<Avis, Integer> {
    List<Avis> findAvisByStatut(boolean statut);
}
