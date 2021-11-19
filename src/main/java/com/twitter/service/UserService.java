package com.twitter.service;

import com.twitter.dto.JwtAuthenticationResponse;
import com.twitter.exception.InvalidArgumentException;
import com.twitter.repository.UserRepository;
import com.twitter.dto.User;
import com.twitter.security.JwtTokenProvider;
import com.twitter.security.PasswordUtility;
import com.twitter.utility.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.twitter.exception.ErrorCode.*;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final SessionService sessionService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserService(UserRepository userRepository,
                       SessionService sessionService,
                       JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.sessionService = sessionService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public JwtAuthenticationResponse createUser(User user) {
        Long sessionId = sessionService.findIdBySessionValue(user.getSession().getSessionValue());
        // If invalid session throw error
        if(sessionId == null) throw new InvalidArgumentException(TWTR10002);

        // If username or email is taken, throw error
        checkUsernameOrEmailExists(user.getUsername(), user.getEmail());
        String uuid = Utility.getUuid();
        user.setUuid(uuid);
        // Create user & update user id in session table
        createUserAndUpdateSession(user, sessionId);

        return getJwtToken(uuid, user.getSession().getSessionValue());
    }

    public JwtAuthenticationResponse login(){
        return getJwtToken("", "");
    }

    public void deleteToken(String sessionId){
    }

    private void checkUsernameOrEmailExists(String userName,
                                            String email){
        if(userRepository.checkUsernameOrEmailExists(userName, email))
            throw new InvalidArgumentException(TWTR10003);
    }

    private void createUserAndUpdateSession(User user,
                                            Long sessionId){
        user.setPasswordHash(PasswordUtility.hashPassword(user.getPassword()));

        Long userId = userRepository.createUser(user);
        log.info("saved user with id {} ", userId);
        sessionService.updateUserId(sessionId, userId);
    }

    private JwtAuthenticationResponse getJwtToken(String uuid,
                                                  String sessionId){
        return JwtAuthenticationResponse
                .builder()
                .accessToken(jwtTokenProvider.generateToken(uuid, sessionId))
                .build();
    }

}
