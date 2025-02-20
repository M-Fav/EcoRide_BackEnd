package fr.ecoride.backend.service;

import fr.ecoride.backend.model.Statistique;
import fr.ecoride.backend.repository.StatistiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StatistiqueService {

    @Autowired
    public final StatistiqueRepository statistiqueRepository;

    public StatistiqueService(StatistiqueRepository statistiqueRepository) {
        this.statistiqueRepository = statistiqueRepository;
    }

    /**
     * Permet de retourner une liste d'avis par
     * rapport à un statut
     * @return
     */
    @Transactional
    public List<Statistique> getStatistiquesForAPeriode(String mois, String annee) {
        return statistiqueRepository.findByMonthAndYear(mois, annee);
    }

}
