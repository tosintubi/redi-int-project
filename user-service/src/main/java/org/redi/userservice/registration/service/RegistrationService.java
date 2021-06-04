package org.redi.userservice.registration.service;

import lombok.AllArgsConstructor;
import org.redi.userservice.appuser.service.AppUserService;
import org.redi.userservice.registration.model.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final AppUserService appUserService;

    //
    public String register(RegistrationRequest request) {
        return "works!";
    }
}
