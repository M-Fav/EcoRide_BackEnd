package fr.ecoride.backend.repository;

import fr.ecoride.backend.model.Covoiturage;
import fr.ecoride.backend.model.CovoituragePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CovoiturageRepository extends JpaRepository<Covoiturage, CovoituragePK> {
    List<Covoiturage> findByLieuDepartAndLieuArrivee(String lieuDepart, String lieuArrivee);
}
