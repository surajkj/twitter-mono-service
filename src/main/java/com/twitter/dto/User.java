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

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User {

    private Long id;

    @NotEmpty
    @Size(min = 1, max = 20)
    @JsonView(FrontendViews.CreateUserView.class)
    private String username;

    @NotEmpty
    @Email
    @JsonView(FrontendViews.CreateUserView.class)
    private String email;

    @NotNull
    @Size(min = 1, max = 50)
    @JsonView(FrontendViews.CreateUserView.class)
    private String name;

    @NotNull
    @Size(min = 5, max = 50)
    @JsonView(FrontendViews.CreateUserView.class)
    private String password;

    @JsonIgnore
    private String passwordHash;

    private DateTime dob;

    private Gender gender;

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

    private String uuid;

 }