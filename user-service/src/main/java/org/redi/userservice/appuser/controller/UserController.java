package org.redi.userservice.appuser.controller;

import lombok.AllArgsConstructor;
import org.redi.userservice.appuser.model.AppUser;
import org.redi.userservice.appuser.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final AppUserService appUserService;


    @GetMapping(path = "/{id}")
    public AppUser userBasicInfoById(@PathVariable("id") Long id) {
        return appUserService.userBasicInfoById(id);
    }
}
