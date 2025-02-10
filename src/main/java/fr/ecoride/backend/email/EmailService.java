package fr.ecoride.backend.email;

import fr.ecoride.backend.service.CovoitureurService;
import fr.ecoride.backend.utils.Constantes;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
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
}

