package com.twitter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JwtAuthenticationResponse {
    private final String tokenType = "Bearer";
    private String accessToken;
}
