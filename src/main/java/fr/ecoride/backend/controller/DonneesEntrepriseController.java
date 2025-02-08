package fr.ecoride.backend.controller;

import fr.ecoride.backend.dto.donneesentreprise.DonneesEntrepriseResponseDTO;
import fr.ecoride.backend.service.DonneesEntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/donneesEntreprise")
public class DonneesEntrepriseController {

    @Autowired
    DonneesEntrepriseService donneesEntrepriseService;

    @GetMapping("/donneesEntreprise")
    public List<DonneesEntrepriseResponseDTO> getAllDonneesEntreprise() {
       return donneesEntrepriseService.getAllDonneesEntreprise();
    }
}
