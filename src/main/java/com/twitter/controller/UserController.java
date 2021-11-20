package com.twitter.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.twitter.dto.JwtAuthenticationResponse;
import com.twitter.dto.User;
import com.twitter.service.SecurityService;
import com.twitter.service.UserService;
import com.twitter.utility.FrontendViews;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

@Slf4j
@RestController
@RequestMapping("api/v1/user")
@Tag(name = "User")
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;

    @Autowired
    public UserController(UserService userService,
                          SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @Operation(description = "Create user", summary = "User will be created & OTP will be sent to email")
    public JwtAuthenticationResponse createUser(@JsonView(FrontendViews.CreateUserView.class) @Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping( value = "login", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @Operation(description = "Create user", summary = "User will be created & OTP will be sent to email")
    public JwtAuthenticationResponse login(@JsonView(FrontendViews.LoginUserView.class) @RequestBody User user) {
        return userService.login(user);
    }

    @DeleteMapping
    @Operation(description = "delete user token", summary = "No APIs will be accessible with this token if token is deleted")
    public void deleteToken(){
        log.info("delete token request");
        userService.deleteToken(securityService.getSessionId());
    }

}
