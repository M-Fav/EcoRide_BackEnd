package fr.ecoride.backend.controller;

import fr.ecoride.backend.dto.avis.AvisRequestDTO;
import fr.ecoride.backend.model.Avis;
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

    @GetMapping("/findAvisByStatut")
    public ResponseEntity findAvisByStatut(@RequestBody Avis request) {
        return ResponseEntity.ok(avisService.findAvisByStatut(request.getStatut()));
    }

}

