package fr.ecoride.backend.repository;

import fr.ecoride.backend.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    Optional<Token> findByAccessToken(String token);

    Optional<Token> findByRefreshToken(String token);

    List<Token> findByUserId(Integer userId);
}
