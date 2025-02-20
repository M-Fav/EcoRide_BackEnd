package fr.ecoride.backend.service;

import fr.ecoride.backend.enums.UserRoleEnum;
import fr.ecoride.backend.enums.UserStatutEnum;
import fr.ecoride.backend.exception.CustomException;
import fr.ecoride.backend.model.User;
import fr.ecoride.backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImp(UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
        return userRepository.findByPseudo(pseudo)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    /**
     * permet de trouver un utilisateur par son pseudo
     * @param pseudo
     * @return
     * @throws UsernameNotFoundException
     */
    @Transactional
    public User findUtilisateurByPseudo(String pseudo) throws UsernameNotFoundException {
        return userRepository.findByPseudo(pseudo)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    /**
     * Permet de soustraire des crédits à un utilisateur
     *
     * @param utilisateurId
     * @param credits
     */
    @Transactional
    public void updateCredits(Integer utilisateurId, float credits, boolean isAddition){

        // On récupère l'utilisateur
        User user = userRepository.findByUtilisateurId(utilisateurId);

        // On calcule le nouveau crédit
        float creditResult = isAddition ? user.getCredit() + credits : user.getCredit() - credits;

        // Vérification uniquement si c'est une soustraction
        if (!isAddition && creditResult < 0) {
            throw new CustomException("Les crédits que vous avez sont insuffisants, veuillez acheter", HttpStatus.FORBIDDEN);
        }

        // Mise à jour du crédit de l'utilisateur
        user.setCredit(creditResult);

        // Sauvegarde en base
        userRepository.save(user);
    }

    /**
     * Permet de récupérer un utilisateur
     *
     * @param utilisateurId
     * @return user
     */
    @Transactional
    public User getUser(Integer utilisateurId){
        // On récupère l'utilisateur
        return userRepository.findByUtilisateurId(utilisateurId);
    }

    /**
     * Permet de trouver la liste des utilisateur pour un role
     * @param role
     * @return liste des user du role entrée
     */
    @Transactional
    public List<User> getUsersByRole(UserRoleEnum role) {
        return userRepository.findByRole(role);
    }

    /**
     * Permet de Susupendre ou actif un compte User
     * @param utilisateurId
     * @return user
     */
    @Transactional
    public void gererStatutUser(Integer utilisateurId, UserStatutEnum statut) {
        //on récupère l'utilisateur
        User user = userRepository.findByUtilisateurId(utilisateurId);

        user.setStatut(statut);
        userRepository.save(user);
    }
}
