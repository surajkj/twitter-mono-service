package com.twitter.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.twitter.dto.User;
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

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @Operation(description = "Create user", summary = "User will be created & OTP will be sent to email")
    @JsonView(FrontendViews.CreateUserResponseView.class)
    public User createUser(@JsonView(FrontendViews.CreateUserView.class) @Valid @RequestBody User user) {
        return userService.createUser(user);
    }

}
