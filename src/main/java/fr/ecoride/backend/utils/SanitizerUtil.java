package fr.ecoride.backend.utils;

import org.apache.commons.text.StringEscapeUtils;

public class SanitizerUtil {
    /**
     * Nettoie une chaîne contre les injections XSS en échappant les caractères HTML dangereux.
     * Exemple : <script> => &lt;script&gt;
     */
    public static String sanitizeHtml(String input) {
        return input == null ? null : StringEscapeUtils.escapeHtml4(input);
    }

    /**
     * Nettoie une chaîne basiquement en supprimant les caractères problématiques.
     */
    public static String strictSanitize(String input) {
        return input == null ? null : input.replaceAll("[<>\"`;/\\\\]", "");
    }

}
