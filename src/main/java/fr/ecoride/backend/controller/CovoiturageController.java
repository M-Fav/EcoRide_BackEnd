package fr.ecoride.backend.controller;

import fr.ecoride.backend.dto.covoiturage.CovoiturageRequestDTO;
import fr.ecoride.backend.dto.covoitureur.CovoitureurRequestDTO;
import fr.ecoride.backend.enums.CovoitureurRoleEnum;
import fr.ecoride.backend.model.Covoiturage;
import fr.ecoride.backend.model.Covoitureur;
import fr.ecoride.backend.service.CovoiturageService;
import fr.ecoride.backend.service.CovoitureurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/covoiturage")
public class CovoiturageController {

    @Autowired
    private CovoiturageService covoiturageService;

    @Autowired
    private CovoitureurService covoitureurService;

    @GetMapping("/covoiturages")
    public ResponseEntity findCovoiturages(@RequestBody Covoiturage request) {
        return ResponseEntity.ok(covoiturageService.findCovoiturages(request.getLieuDepart(), request.getLieuArrivee()));
    }

    @PostMapping("/createCovoiturage")
    public void createCovoiturage(@RequestBody CovoiturageRequestDTO covoiturageRequestDTO) {
        CovoitureurRequestDTO covoitureurRequestDTO = new CovoitureurRequestDTO(CovoitureurRoleEnum.CONDUCTEUR,
                covoiturageRequestDTO.getUtilisateurId(),
                covoiturageService.createCovoiturage(covoiturageRequestDTO));
        covoitureurService.createCovoitureur(covoitureurRequestDTO);
    }
}
