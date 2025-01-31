package fr.ecoride.backend.controller;

import fr.ecoride.backend.dto.voiture.VoitureRequestDTO;
import fr.ecoride.backend.service.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/voitures")
public class VoitureController {

    @Autowired
    private VoitureService voitureService;

    @PostMapping("/creationVoiture")
    public void createVoiture(@RequestBody VoitureRequestDTO voitureRequestDTO) {
        voitureService.createVoiture(voitureRequestDTO);
    }


}