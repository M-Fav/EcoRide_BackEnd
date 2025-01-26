package fr.ecoride.backend.repository;

import fr.ecoride.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByPseudo(String pseudo);
}
