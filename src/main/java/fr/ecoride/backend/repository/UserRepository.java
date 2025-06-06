package fr.ecoride.backend.repository;

import fr.ecoride.backend.enums.UserRoleEnum;
import fr.ecoride.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByPseudo(String pseudo);

    User findByUtilisateurId(Integer utilisateurId);

    List<User> findByRole(UserRoleEnum role);

}
