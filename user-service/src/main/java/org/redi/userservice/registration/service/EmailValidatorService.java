package org.redi.userservice.registration.service;

import org.redi.userservice.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidatorService implements Predicate<String> {

    @Override
    public boolean test(String email) {
        return Utils.isValidEmail(email);
    }
}
