package fr.ecoride.backend.repository;

import fr.ecoride.backend.model.Statistique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatistiqueRepository extends JpaRepository<Statistique, Integer> {

    @Query("SELECT s FROM Statistique s WHERE FUNCTION('YEAR', FUNCTION('STR_TO_DATE', s.date, '%d-%m-%Y')) = :year AND FUNCTION('MONTH', FUNCTION('STR_TO_DATE', s.date, '%d-%m-%Y')) = :month")
    List<Statistique> findByMonthAndYear(@Param("month") String month, @Param("year") String year);

}
