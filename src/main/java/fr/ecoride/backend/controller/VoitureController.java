package fr.ecoride.backend.controller;

import fr.ecoride.backend.model.Voiture;
import fr.ecoride.backend.repository.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voitures")
public class VoitureController {

    @Autowired
    private VoitureRepository voitureRepository;

    @GetMapping
    public List<Voiture> getAllVoitures() {
        return voitureRepository.findAll();
    }

    @GetMapping("/{voitureId}")
    public Voiture getVoitureById(@PathVariable Long voitureId) {
        return  voitureRepository.findById(voitureId).orElseThrow(() -> new RuntimeException("Voiture introuvable"));
    }

    @PostMapping
    public Voiture createVoiture(@RequestBody Voiture voiture) {
        return voitureRepository.save(voiture);
    }

    @PutMapping("/{voitureId}")
    public Voiture updateVoiture(@PathVariable Long voitureId, @RequestBody Voiture updateVoiture) {
        return voitureRepository.findById(voitureId).map(voiture -> {
            voiture.setModele(updateVoiture.getModele());
            voiture.setImmatriculation(updateVoiture.getImmatriculation());
            voiture.setEnergie(updateVoiture.getEnergie());
            voiture.setCouleur(updateVoiture.getCouleur());
            voiture.setDatePremiereImmatriculation(updateVoiture.getDatePremiereImmatriculation());
            return voitureRepository.save(voiture);
        }).orElseThrow(() -> new RuntimeException("Voiture introuvable pour mise à jour !"));
    }

    @DeleteMapping("/{voitureId}")
    public String deleteVoiture(@PathVariable Long voitureId) {
        voitureRepository.deleteById(voitureId);
        return "Voiture supprimée avec succès !";
    }

    @GetMapping("/test")
    public String test() {
        return "l'accès Voiture fonctionne !";
    }
}