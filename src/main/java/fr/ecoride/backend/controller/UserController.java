package fr.ecoride.backend.controller;

import fr.ecoride.backend.dto.utilisateur.UtilisateurRequestDTO;
import fr.ecoride.backend.dto.utilisateur.UtilisateurResponseDTO;
import fr.ecoride.backend.model.User;
import fr.ecoride.backend.service.UserDetailsServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utilisateur")
public class UserController {

    @Autowired
    UserDetailsServiceImp userDetailsServiceImp;


    @PutMapping("/gererStatutUtilisateur")
    public void gererStatutUtilisateur(@RequestBody UtilisateurRequestDTO utilisateurRequestDTO) {

        userDetailsServiceImp.gererStatutUser(utilisateurRequestDTO.getUtilisateurId(),
                utilisateurRequestDTO.getStatut());

    }

    @GetMapping("/trouverUtilisateur")
    public ResponseEntity trouverUtilisateur(@RequestBody UtilisateurRequestDTO utilisateurRequestDTO) {

        User user = userDetailsServiceImp.findUtilisateurByPseudo(utilisateurRequestDTO.getPseudo());
        UtilisateurResponseDTO utilisateurResponseDTO = new UtilisateurResponseDTO();

        utilisateurResponseDTO.setUtilisateurId(user.getUtilisateurId());
        utilisateurResponseDTO.setNom(user.getNom());
        utilisateurResponseDTO.setPrenom(user.getPrenom());


        return ResponseEntity.ok(utilisateurResponseDTO);
    }
}
