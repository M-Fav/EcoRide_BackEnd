package fr.ecoride.backend.security.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ecoride.backend.dto.ErreurDTO;
import fr.ecoride.backend.service.JwtService;
import fr.ecoride.backend.service.UserDetailsServiceImp;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsServiceImp userDetailsService;


    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsServiceImp userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Filtre de sécurité Jwt
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // Si l'URL est dans la liste des routes exemptées, on ignore le filtrage JWT
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/login") || requestURI.startsWith("/register")
                || requestURI.startsWith("/refresh_token") || requestURI.startsWith("/donneesEntreprise")) {
            filterChain.doFilter(request, response);
            return;
        }

        //Contrôle de la présence de l'authentification Bearer token
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            sendErrorResponse(response, "Token manquant ou invalide", HttpStatus.UNAUTHORIZED);
            return;
        }

        //Suppression de la partie "Bearer"
        String token = authHeader.substring(7);

        try {
            //Récupération du nom de l'utilistateur
            String username = jwtService.extractUsername(token);

            //Si l'utilisateur n'existe pas
            if (username == null) {
                sendErrorResponse(response, "Nom d'utilisateur introuvable dans le token", HttpStatus.UNAUTHORIZED);
                return;
            }

            // Vérification d'une authentification existante
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                //On contrôle que le token soit valide
                if (!jwtService.isValid(token, userDetails)) {
                    sendErrorResponse(response, "Token invalide ou expiré", HttpStatus.UNAUTHORIZED);
                    return;
                }

                // Authentification réussie
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
                );
            }
        } catch (Exception e) {
            sendErrorResponse(response, "Erreur lors de la validation du token", HttpStatus.INTERNAL_SERVER_ERROR);
            return;
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Méthode qui permet de retourner une erreur
     *
     * @param response
     * @param message
     * @param status
     * @throws IOException
     */
    private void sendErrorResponse(HttpServletResponse response, String message, HttpStatus status) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ErreurDTO errorResponse = new ErreurDTO(
                status.value(),
                status.name(),
                message
        );

        String jsonResponse = new ObjectMapper().writeValueAsString(errorResponse);
        response.getWriter().write(jsonResponse);
    }
}
