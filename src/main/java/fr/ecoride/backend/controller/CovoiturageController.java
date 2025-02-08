package fr.ecoride.backend.controller;

import fr.ecoride.backend.dto.covoiturage.CovoiturageRequestDTO;
import fr.ecoride.backend.model.Covoiturage;
import fr.ecoride.backend.service.CovoiturageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController("/covoiturage")
public class CovoiturageController {

    @Autowired
    private CovoiturageService covoiturageService;

    @GetMapping("/covoiturages")
    public ResponseEntity findCovoiturages(@RequestBody Covoiturage request) {
        return ResponseEntity.ok(covoiturageService.findCovoiturages(request.getLieuDepart(), request.getLieuArrivee()));
    }

    @PostMapping("/createCovoiturage")
    public void createCovoiturage(@RequestBody CovoiturageRequestDTO covoiturageRequestDTO) {
        covoiturageService.createCovoiturage(covoiturageRequestDTO);
    }
}
