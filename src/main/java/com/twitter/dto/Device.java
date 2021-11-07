package com.twitter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Device {

    private String userAgent;
    private String agentType;
    private String agentName;
    private String agentVersion;
    private String agentProducer;

    private String os;
    private String osProducer;
    private String osVersion;
    private String deviceType;

}
