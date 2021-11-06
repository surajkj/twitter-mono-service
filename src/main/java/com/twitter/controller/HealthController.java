package com.twitter.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.twitter.dto.Health;
import com.twitter.utility.FrontendViews;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;

@Slf4j
@RestController
@RequestMapping("api/v1/health")
@Tag(name = "Health")
public class HealthController {

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    @JsonView({FrontendViews.HealthView.class})
    @Operation(description = "Shows status of application & dependent services", summary = "View application health")
    public Health get(){
        log.info("Hello world!");
        return Health.builder().applicationStatus(true).databaseStatus(false).build();
    }
}
