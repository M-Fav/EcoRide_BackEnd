package fr.ecoride.backend.repository;

import fr.ecoride.backend.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    Optional<Token> findByAccessToken(String token);

    Optional<Token> findByRefreshToken(String token);

    List<Token> findByUtilisateurId(Integer utilisateurId);
}
