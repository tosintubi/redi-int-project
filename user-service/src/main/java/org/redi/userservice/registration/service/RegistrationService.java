package org.redi.userservice.registration.service;

import lombok.AllArgsConstructor;
import org.redi.userservice.appuser.model.AppUser;
import org.redi.userservice.appuser.model.AppUserRole;
import org.redi.userservice.appuser.service.AppUserService;
import org.redi.userservice.registration.model.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidatorService emailValidator;
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
}
