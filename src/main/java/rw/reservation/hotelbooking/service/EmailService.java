package rw.reservation.hotelbooking.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String fromAddress;

    // Send email with template
    @Async
    public void sendEmail(String to, String subject, Context context) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

            helper.setFrom("Luxe Stays Hotel <" + fromAddress + ">");
            helper.setSubject(subject);
            helper.setTo(to);

            String htmlContent = templateEngine.process("notification-email", context);
            helper.setText(htmlContent, true);

            mailSender.send(mimeMessage);
            System.out.println("Email sent successfully, to: "+ to);
        } catch (Exception e) {
            System.out.println("Email not sent:: " + e.getMessage());
            // log.error("Email not sent:: " + e.getMessage());
        }
    }
}