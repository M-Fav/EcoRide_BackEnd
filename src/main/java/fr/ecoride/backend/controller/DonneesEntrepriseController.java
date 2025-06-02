package fr.ecoride.backend.controller;

import fr.ecoride.backend.dto.donneesentreprise.DonneesEntrepriseResponseDTO;
import fr.ecoride.backend.firebase.collections.DonneesEntreprise;
import fr.ecoride.backend.service.DonneesEntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping()
public class DonneesEntrepriseController {

    @Autowired
    DonneesEntreprise donneesEntreprise;

    @GetMapping("/donneesEntreprise")
    public List<DonneesEntrepriseResponseDTO> getAllDonneesEntreprise() throws Exception {
       return donneesEntreprise.getAllDonneesEntreprise();
    }
}
