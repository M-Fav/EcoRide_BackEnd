package fr.ecoride.backend.service;

import fr.ecoride.backend.exception.CustomException;
import fr.ecoride.backend.model.User;
import fr.ecoride.backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImp(UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
        return userRepository.findByPseudo(pseudo)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    /**
     * Permet de soustraire des crédits à un utilisateur
     *
     * @param utilisateurId
     * @param prixCovoit
     */
    public void updateCredits(Integer utilisateurId, float prixCovoit, boolean isAddition){

        // On récupère l'utilisateur
        User user = userRepository.findByUtilisateurId(utilisateurId);

        // On calcule le nouveau crédit
        float creditResult = isAddition ? user.getCredit() + prixCovoit : user.getCredit() - prixCovoit;

        // Vérification uniquement si c'est une soustraction
        if (!isAddition && creditResult < 0) {
            throw new CustomException("Les crédits que vous avez sont insuffisants, veuillez acheter", HttpStatus.FORBIDDEN);
        }

        // Mise à jour du crédit de l'utilisateur
        user.setCredit(creditResult);

        // Sauvegarde en base
        userRepository.save(user);
    }
}
