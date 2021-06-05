package org.redi.userservice.email;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class EmailSenderService implements EmailSender {

    // private final JavaMailSender mailSender;

    @Override
    public void sendRegistrationEmail(String to, String email) {
         // TODO: use java mail sender
    }
}
