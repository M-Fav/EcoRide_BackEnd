package fr.ecoride.backend.controller;

import fr.ecoride.backend.dto.statistique.StatistiqueResponseDTO;
import fr.ecoride.backend.mapper.StatistiqueMapper;
import fr.ecoride.backend.service.StatistiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/statistiques")
public class StatistiquesController {

    @Autowired
    private StatistiqueService statistiqueService;

    @GetMapping("/getStatistiquesForAPeriode")
    public ResponseEntity getStatistiquesForAPeriode(
            @RequestParam String mois,
            @RequestParam String annee) {

        List<StatistiqueResponseDTO> listeStatistique = StatistiqueMapper.INSTANCE.toStatistiqueResponseDTO(
                statistiqueService.getStatistiquesForAPeriode(mois, annee));

        return ResponseEntity.ok(listeStatistique);
    }


}

