package org.redi.userservice.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private final JavaMailSender mailSender ;
    private final static Logger LOGGER = LoggerFactory.getLogger((EmailService.class));

    @Override
    @Async // Async Method
    public void sendRegistrationEmail(String recipientEmail, String emailMessage) {

        try {

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            mimeMessageHelper.setText(emailMessage, true);
            mimeMessageHelper.setTo(recipientEmail);
            mimeMessageHelper.setSubject("Confirm your email");
            mimeMessageHelper.setFrom("hello@redibooks.org");
            mailSender.send(mimeMessage);
        } catch (MessagingException exception){
            LOGGER.error("Error sending Email: ", exception);
            throw new IllegalStateException(" Failed to send registration email");
        }
    }
}
