package fr.ecoride.backend.repository;

import fr.ecoride.backend.model.Covoiturage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CovoiturageRepository extends JpaRepository<Covoiturage, Integer> {
    List<Covoiturage> findByLieuDepartAndLieuArrivee(String lieuDepart, String lieuArrivee);

    Covoiturage findByCovoiturageId(Integer covoiturageId);
}
