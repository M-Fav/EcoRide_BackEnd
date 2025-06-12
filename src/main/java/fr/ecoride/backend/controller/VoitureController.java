package fr.ecoride.backend.controller;

import fr.ecoride.backend.dto.voiture.VoitureRequestDTO;
import fr.ecoride.backend.dto.voiture.VoitureResponseDTO;
import fr.ecoride.backend.service.VoitureService;
import fr.ecoride.backend.utils.SanitizerUtil; // ✅ Ajout de l'import

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

        voitureRequestDTO.setMarque(SanitizerUtil.sanitizeHtml(voitureRequestDTO.getMarque()));
        voitureRequestDTO.setModele(SanitizerUtil.sanitizeHtml(voitureRequestDTO.getModele()));
        voitureRequestDTO.setCouleur(SanitizerUtil.sanitizeHtml(voitureRequestDTO.getCouleur()));
        voitureRequestDTO.setImmatriculation(SanitizerUtil.strictSanitize(voitureRequestDTO.getImmatriculation()));

        voitureService.createVoiture(voitureRequestDTO);
    }

    @GetMapping("/listeVoiture")
    public List<VoitureResponseDTO> getAllVoitures() {
        return voitureService.getAllVoitures();
    }

    @GetMapping("/recupererVoituresUtilisateur")
    public List<VoitureResponseDTO> getVoituresUtilisateur(
            @RequestParam(defaultValue = "") Integer utilisateurId) {
        return voitureService.getVoituresUtilisateur(utilisateurId);
    }
}
