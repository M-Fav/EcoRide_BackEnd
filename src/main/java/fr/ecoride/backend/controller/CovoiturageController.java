package fr.ecoride.backend.controller;

import fr.ecoride.backend.model.Covoiturage;
import fr.ecoride.backend.service.CovoiturageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class CovoiturageController {

    private CovoiturageService covoiturageService;

    public CovoiturageController(CovoiturageService covoiturageService) {
        this.covoiturageService = covoiturageService;
    }


    @GetMapping("/covoiturages")
    public ResponseEntity findCovoiturages(@RequestBody Covoiturage request) {
        return ResponseEntity.ok(covoiturageService.findCovoiturages(request.getLieuDepart(), request.getLieuArrivee()));
    }
}
