package com.twitter.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Gender {

    @JsonProperty("Male")
    male("Male"),

    @JsonProperty("Female")
    female("Female"),

    @JsonProperty("Other")
    other("Other");

    private final String displayText;

    Gender(String displayText) {
        this.displayText = displayText;
    }

    @Override
    public String toString() {
        return displayText;
    }

}
