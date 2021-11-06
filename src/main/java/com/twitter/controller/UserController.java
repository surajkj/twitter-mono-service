package com.twitter.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;

@Slf4j
@RestController
@RequestMapping("api/v1/health")
@Tag(name = "Health")
public class UserController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON)
    public void createUser(){

    }


}
