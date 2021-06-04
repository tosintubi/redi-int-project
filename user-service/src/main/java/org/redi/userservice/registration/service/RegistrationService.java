package org.redi.userservice.registration.service;

import lombok.AllArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.redi.userservice.appuser.model.AppUser;
import org.redi.userservice.appuser.model.AppUserRole;
import org.redi.userservice.appuser.service.AppUserService;
import org.redi.userservice.registration.model.RegistrationRequest;
import org.redi.userservice.registration.token.ConfirmationToken;
import org.redi.userservice.registration.token.ConfirmationTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidatorService emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    //
    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException(String.format("%s email is not in the valid format"));
        }

        return appUserService.signUpUser(new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER // Assumes USER
        ));
    }

    @Transactional
    public String confirmToken(String token){
        // Checks for token
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("Token not found")
        );

        // Token exists
        // Confirmation already happened because there's an entry confirmed at.
        if (confirmationToken.isConfirmed()){
            throw new IllegalStateException("Email has already been confirmed");
        }

        // Time has passed and token has expired
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("Token has already expired");
        }

        // Token exists and is still valid
        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());

        return "confirmed";
    }
}
