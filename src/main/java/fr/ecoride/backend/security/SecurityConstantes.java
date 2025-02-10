package fr.ecoride.backend.security;

import java.util.List;

public class SecurityConstantes {
        public static final List<String> PUBLIC_URLS = List.of(
                "/login/**",
                "/register/**",
                "/refresh_token/**",
                "/donneesEntreprise/**"
        );
}
