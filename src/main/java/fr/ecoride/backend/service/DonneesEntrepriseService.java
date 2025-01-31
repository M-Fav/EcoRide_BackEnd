package fr.ecoride.backend.service;

import fr.ecoride.backend.dto.donneesentreprise.DonneesEntrepriseResponseDTO;
import fr.ecoride.backend.repository.DonneesEntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonneesEntrepriseService {

    @Autowired
    DonneesEntrepriseRepository donneesEntrepriseRepository;

    public List<DonneesEntrepriseResponseDTO> getAllDonneesEntreprise() {
        return donneesEntrepriseRepository.findAll()
                .stream()
                .map(donneesEntreprise -> new DonneesEntrepriseResponseDTO(donneesEntreprise.getLibelle(), donneesEntreprise.getValeur()))
                .collect(Collectors.toList());
    }
}
