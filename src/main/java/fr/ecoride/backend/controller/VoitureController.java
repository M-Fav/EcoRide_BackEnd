package fr.ecoride.backend.controller;

import fr.ecoride.backend.dto.donneesentreprise.DonneesEntrepriseResponseDTO;
import fr.ecoride.backend.dto.voiture.VoitureRequestDTO;
import fr.ecoride.backend.dto.voiture.VoitureResponseDTO;
import fr.ecoride.backend.service.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voitures")
public class VoitureController {

    @Autowired
    private VoitureService voitureService;

    @PostMapping("/creationVoiture")
    public void createVoiture(@RequestBody VoitureRequestDTO voitureRequestDTO) {
        voitureService.createVoiture(voitureRequestDTO);
    }

    @GetMapping("/listeVoiture")
    public List<VoitureResponseDTO> getAllVoitures() {
        return voitureService.getAllVoitures();
    }


}