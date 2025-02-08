package fr.ecoride.backend.controller;

import fr.ecoride.backend.dto.covoitureur.CovoitureurRequestDTO;
import fr.ecoride.backend.service.CovoitureurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/covoitureur")
public class CovoitureurController {

    @Autowired
    private CovoitureurService covoitureurService;

    @PostMapping("/createCovoitureur")
    public void createCovoitureur(@RequestBody CovoitureurRequestDTO covoitureurRequestDTO) {
        covoitureurService.createCovoitureur(covoitureurRequestDTO);
    }
}
