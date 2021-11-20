package com.twitter.service;

import com.twitter.security.UserAdditionalDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SecurityService {

    public String getUserUUID() {
        String uuid = null;
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            uuid = authentication.getName();
        }
        return uuid;
    }

    public String getSessionValue() {
        String sessionId = null;
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            Object object =  authentication.getDetails();
            if(!(object instanceof UserAdditionalDetails))
                return null;
            UserAdditionalDetails details = (UserAdditionalDetails) authentication.getDetails();
            if(details != null) {
                sessionId = details.getSession().getSessionValue();
            }
        }
        return sessionId;
    }

    public Long getSessionId() {
        Long id = null;
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            Object object =  authentication.getDetails();
            if(!(object instanceof UserAdditionalDetails))
                return null;
            UserAdditionalDetails details = (UserAdditionalDetails) authentication.getDetails();
            if(details != null) {
                id = details.getSession().getId();
            }
        }
        return id;
    }

}
