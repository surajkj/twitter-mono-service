package com.twitter.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.twitter.dto.Session;
import com.twitter.service.SessionService;
import com.twitter.utility.FrontendViews;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;

@Slf4j
@RestController
@RequestMapping("api/v1/session")
@Tag(name = "Session")
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService){
        this.sessionService = sessionService;
    }

    @JsonView(FrontendViews.CreateSessionView.class)
    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Session createSession(){
        return sessionService.createSession();
    }

}
