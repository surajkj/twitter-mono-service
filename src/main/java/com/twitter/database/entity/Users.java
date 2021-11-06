package com.twitter.database.entity;

import com.twitter.enums.Gender;
import lombok.Data;
import lombok.ToString;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@ToString
public class Users {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String name;

    private DateTime dob;

    private Gender gender;

    private String bio;

    private String profilePhoto;

    private String headerPhoto;

    private String website;

    private boolean isActive;

    private DateTime createdTime;

}
