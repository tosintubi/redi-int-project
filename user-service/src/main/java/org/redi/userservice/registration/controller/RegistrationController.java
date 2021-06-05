package org.redi.userservice.registration.controller;

import lombok.AllArgsConstructor;
import org.redi.userservice.registration.model.RegistrationRequest;
import org.redi.userservice.registration.service.RegistrationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/add")
    public String signUp (@RequestBody RegistrationRequest request){
       return registrationService.register(request);
    }

    @GetMapping(path = "/confirm")
    public String confirmToken(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}