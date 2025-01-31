package fr.ecoride.backend.repository;

import fr.ecoride.backend.model.DonneesEntreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonneesEntrepriseRepository extends JpaRepository<DonneesEntreprise, Integer> {
}
