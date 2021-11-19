package com.twitter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.twitter.utility.FrontendViews;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Session {

    private Long id;

    @JsonView({FrontendViews.CreateSessionView.class,FrontendViews.CreateUserView.class, FrontendViews.LoginUserView.class})
    private String sessionValue;

    private Long deviceId;
    private Long userId;
    private String ipAddress;

}
