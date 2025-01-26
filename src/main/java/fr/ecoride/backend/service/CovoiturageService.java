package fr.ecoride.backend.service;

import fr.ecoride.backend.model.Covoiturage;
import fr.ecoride.backend.model.CovoiturageResponse;
import fr.ecoride.backend.repository.CovoiturageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CovoiturageService {

    private final CovoiturageRepository covoiturageRepository;

    public CovoiturageService(CovoiturageRepository covoiturageRepository) {
        this.covoiturageRepository = covoiturageRepository;
    }

    public List<CovoiturageResponse> findCovoiturages(String lieuDepart, String lieuArrivee) {

        List<CovoiturageResponse> listeCovoiturageResponse = new ArrayList<CovoiturageResponse>();
        List<Covoiturage> listeCovoiturage = covoiturageRepository.findByLieuDepartAndLieuArrivee(lieuDepart, lieuArrivee);
        for (Covoiturage covoiturage : listeCovoiturage) {
            CovoiturageResponse covoiturageResponse =
                    new CovoiturageResponse(covoiturage.getLieuDepart(), covoiturage.getLieuArrivee(), covoiturage.getNbPlace());
            listeCovoiturageResponse.add(covoiturageResponse);
        }
        return listeCovoiturageResponse;
    }
}
