package com.twitter.service;

import com.twitter.dto.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SessionService {

    public Session createSession(){
        return Session.builder().sessionValue("test").build();
    }

}
