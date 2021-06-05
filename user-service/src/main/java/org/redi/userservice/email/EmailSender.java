package org.redi.userservice.email;

public interface EmailSender {
    void sendRegistrationEmail( String to, String email);
}
