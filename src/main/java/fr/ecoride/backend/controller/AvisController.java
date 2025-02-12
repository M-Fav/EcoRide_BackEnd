package fr.ecoride.backend.controller;

import fr.ecoride.backend.dto.avis.AvisRequestDTO;
import fr.ecoride.backend.service.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/avis")
public class AvisController {

    @Autowired
    private AvisService avisService;

    @PostMapping("/createAvis")
    public void createAvis(@RequestBody AvisRequestDTO avisRequestDTO) {
        avisService.createAvis(avisRequestDTO);
    }

    @GetMapping("/getAvisATraiter")
    public ResponseEntity getAvisATraiter() {
        return ResponseEntity.ok(avisService.getAvisATraiter());
    }

    @PutMapping("/avisDecision")
    public void traiterAvis(@RequestBody AvisRequestDTO avisRequestDTO) {
        avisService.traiterAvis(avisRequestDTO.getAvisId(), avisRequestDTO.getDecision());
    }

}

