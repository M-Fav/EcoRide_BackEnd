package fr.ecoride.backend.repository;

import fr.ecoride.backend.model.Covoiturage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface CovoiturageRepository extends JpaRepository<Covoiturage, Integer> {
    List<Covoiturage> findByLieuDepartAndLieuArriveeAndDateAndNbPlaceGreaterThan(String lieuDepart,
                                                                                 String lieuArrivee,
                                                                                 LocalDate date,
                                                                                 Integer nbPlace);

    Covoiturage findByCovoiturageId(Integer covoiturageId);
}
