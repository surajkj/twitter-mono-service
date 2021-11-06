package com.twitter.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.twitter.database.entity.Users;
import com.twitter.utility.FrontendViews;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

@Slf4j
@RestController
@RequestMapping("api/v1/user")
@Tag(name = "User")
public class UserController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON)
    @Operation(description = "Create user", summary = "User will be created & OTP will be sent to email")
    public void createUser(@JsonView(FrontendViews.CreateUserView.class) @Valid @RequestBody Users user){
        log.info("Creating user {} ", user);
    }


}
