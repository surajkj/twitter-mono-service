package com.twitter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.twitter.utility.FrontendViews;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonView({FrontendViews.HealthView.class})
public class Health {
    private boolean applicationStatus;
    private boolean databaseStatus;

}
