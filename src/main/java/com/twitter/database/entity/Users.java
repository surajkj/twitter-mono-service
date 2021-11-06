package com.twitter.database.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.twitter.enums.Gender;
import com.twitter.utility.FrontendViews;
import lombok.Data;
import lombok.ToString;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
@ToString
public class Users {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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

}