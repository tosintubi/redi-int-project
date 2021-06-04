package org.redi.userservice.appuser.service;

import lombok.AllArgsConstructor;
import org.redi.userservice.appuser.model.AppUser;
import org.redi.userservice.appuser.repository.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class AppUserService implements UserDetailsService {

    private final static  String USER_NOT_FOUND_MESSAGE = "User with email %s not found";
    private  final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
                String.format(USER_NOT_FOUND_MESSAGE, email)
        ));
    }

    public String signUpUser(AppUser appUser){
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        if (userExists) {
            throw new IllegalStateException("That email is already taken");
        }

        String encodedPassword =  bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword); // encode and replace password

        appUserRepository.save(appUser);
        return "it still works";
    }
}