package com.twitter.service;

import com.twitter.dto.Device;
import com.twitter.dto.Session;
import com.twitter.repository.SessionRepository;
import com.twitter.utility.UserAgentParser;
import com.twitter.utility.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class SessionService {

    private final DeviceService deviceService;
    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(DeviceService deviceService,
                          SessionRepository sessionRepository){
        this.deviceService = deviceService;
        this.sessionRepository = sessionRepository;
    }

    public Session createSession(HttpServletRequest request){
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        String userAgent = request.getHeader("user-agent");
        Long deviceId = deviceService.createDevice(userAgent);
        log.info("Device created with id {} ", deviceId);

        String sessionValue = Utility.getUuid();

        Long sessionId = sessionRepository.createSession(Session
                .builder()
                        .sessionValue(sessionValue)
                        .deviceId(deviceId)
                        .ipAddress(ipAddress)
                .build());
        log.info("session created with id {}", sessionId);

        return Session
                .builder()
                .sessionValue(sessionValue)
                .build();
    }

    public Long findIdBySessionValue(String sessionValue){
        return sessionRepository.findIdBySessionValue(sessionValue);
    }

    public void updateUserId(Long sessionId,
                             Long userId){
        sessionRepository.updateUserId(sessionId, userId);
    }
}
