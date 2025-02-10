package fr.ecoride.backend.email;

import fr.ecoride.backend.exception.CustomException;
import fr.ecoride.backend.model.Covoiturage;
import fr.ecoride.backend.model.Covoitureur;
import fr.ecoride.backend.utils.Constantes;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.util.Map;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private static String ENVOYER_MAIL = "envoyerEmail";
    private static String SEND_EMAIL_SUPPRESSION_COVOITURAGE = "sendEmailSuppressionCovoiturage";
    private static String SEND_EMAIL_VALIDATION_COVOITURAGE = "sendEmailValidationCovoiturage";

    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    /**
     * Methode qui permet l'envoi de mail
     *
     * @param destinataire
     * @param sujet
     * @param templateName
     * @param variables
     * @throws MessagingException
     * @throws jakarta.mail.MessagingException
     */
    @Async
    public void envoyerEmail(String destinataire, String sujet, String templateName, Map<String, Object> variables)
            throws MessagingException, jakarta.mail.MessagingException {
        logger.debug(ENVOYER_MAIL + Constantes.LOG_DEBUT);

        // Charger le template et injecter les variables
        Context context = new Context();
        context.setVariables(variables);
        String contenuHtml = templateEngine.process(templateName, context);

        // Création du mail
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(destinataire);
        helper.setSubject(sujet);
        helper.setText(contenuHtml, true);

        // Ajouter le logo en tant qu'image embarquée
        ClassPathResource imageResource = new ClassPathResource("images/logoEcoRide.png");
        helper.addInline("logoEcoRide", imageResource);

        // Envoi du mail
        mailSender.send(message);

        logger.debug(ENVOYER_MAIL + Constantes.LOG_FIN);
    }

    /**
     * Permet d'envoyer un mail d'information
     * de l'annulation d'un covoiturage
     *
     * @param covoiturage
     */
    public void sendEmailSuppressionCovoiturage(Covoiturage covoiturage, Covoitureur covoitureur, String mailDestinataire){
        logger.debug(SEND_EMAIL_SUPPRESSION_COVOITURAGE + Constantes.LOG_DEBUT);

        //Envoie du mail
        try {
            // Variables dynamiques à injecter dans le template
            Map<String, Object> variables = Map.of(
                    "villeDepart", covoiturage.getLieuDepart(),
                    "villeArrivee", covoiturage.getLieuArrivee(),
                    "dateCovoiturage", covoiturage.getDate().toString(),
                    "heureCovoiturage", covoiturage.getHeureDepart().toString()
            );

            // Appel à la méthode d'envoi d'email
            envoyerEmail(mailDestinataire,
                    "Annulation de votre covoiturage",
                    "mail_covoiturage_annule",
                    variables);

        } catch (Exception e) {
            throw new CustomException( "Erreur lors de l'envoi de l'email : " + e.getMessage(), HttpStatus.FORBIDDEN);
        }

        logger.debug(SEND_EMAIL_SUPPRESSION_COVOITURAGE + Constantes.LOG_FIN);
    }

    public void sendEmailValidationCovoiturage(Covoiturage covoiturage, Covoitureur covoitureur, String mailDestinataire) {
        logger.debug(SEND_EMAIL_VALIDATION_COVOITURAGE + Constantes.LOG_DEBUT);

        //Envoie du mail
        try {
            // Variables dynamiques à injecter dans le template
            Map<String, Object> variables = Map.of(
                    "villeDepart", covoiturage.getLieuDepart(),
                    "villeArrivee", covoiturage.getLieuArrivee(),
                    "dateCovoiturage", covoiturage.getDate().toString(),
                    "heureCovoiturage", covoiturage.getHeureDepart().toString()
            );

            // Appel à la méthode d'envoi d'email
            envoyerEmail(mailDestinataire,
                    "Validation de votre covoiturage",
                    "mail_covoiturage_termine",
                    variables);

        } catch (Exception e) {
            throw new CustomException( "Erreur lors de l'envoi de l'email : " + e.getMessage(), HttpStatus.FORBIDDEN);
        }

        logger.debug(SEND_EMAIL_VALIDATION_COVOITURAGE + Constantes.LOG_FIN);
    }
}

