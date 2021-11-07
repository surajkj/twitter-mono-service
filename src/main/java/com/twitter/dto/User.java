package com.twitter.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.twitter.enums.Gender;
import com.twitter.utility.FrontendViews;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User {

    @JsonView(FrontendViews.CreateUserResponseView.class)
    private Long id;

    @NotNull
    @Min(1)
    @Max(20)
    @JsonView(FrontendViews.CreateUserView.class)
    private String username;

    @NotNull
    @Min(3)
    @Max(50)
    @JsonView(FrontendViews.CreateUserView.class)
    private String email;

    @NotNull
    @Min(1)
    @Max(50)
    @JsonView(FrontendViews.CreateUserView.class)
    private String name;

    @NotNull
    @Min(1)
    @Max(50)
    @JsonView(FrontendViews.CreateUserView.class)
    private String password;

    @JsonIgnore
    private String passwordHash;

    private DateTime dob;

    private Gender gender;

    @Max(250)
    private String bio;

    private String profilePhoto;

    private String headerPhoto;

    @Min(5)
    @Max(100)
    private String website;

    private boolean isActive;

    private DateTime createdTime;

    @JsonView(FrontendViews.CreateUserView.class)
    private Session session;

 }