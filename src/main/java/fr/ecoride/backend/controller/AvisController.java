package fr.ecoride.backend.controller;

import fr.ecoride.backend.model.Avis;
import fr.ecoride.backend.repository.AvisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avis")
public class AvisController {

    @Autowired
    private AvisRepository avisRepository;

    @GetMapping
    public List<Avis> getAllAvis() {
        return avisRepository.findAll();
    }

    @GetMapping("/{avisId}")
    public Avis getAvisById(@PathVariable Long avisId) {
        return  avisRepository.findById(avisId).orElseThrow(() -> new RuntimeException("Avis introuvable"));
    }

    @PostMapping
    public Avis createAvis(@RequestBody Avis avis) {
        return avisRepository.save(avis);
    }

    @PutMapping("/{avisId}")
    public Avis updateAvis(@PathVariable Long avisId, @RequestBody Avis updateAvis) {
        return avisRepository.findById(avisId).map(avis -> {
            avis.setCommentaire(updateAvis.getCommentaire());
            avis.setNote(updateAvis.getNote());
            avis.setStatut(updateAvis.getStatut());
            return avisRepository.save(avis);
        }).orElseThrow(() -> new RuntimeException("Avis introuvable pour mise à jour !"));
    }

    @DeleteMapping("/{avisId}")
    public String deleteAvis(@PathVariable Long avisId) {
        avisRepository.deleteById(avisId);
        return "Avis supprimé avec succès !";
    }

    @GetMapping("/test")
    public String test() {
        return "l'accès Avis fonctionne !";
    }
}

