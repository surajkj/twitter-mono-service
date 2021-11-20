package com.twitter.service;

import com.twitter.dto.JwtAuthenticationResponse;
import com.twitter.exception.InvalidArgumentException;
import com.twitter.exception.ResourceNotFoundException;
import com.twitter.repository.UserRepository;
import com.twitter.dto.User;
import com.twitter.security.JwtTokenProvider;
import com.twitter.security.PasswordUtility;
import com.twitter.utility.Utility;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
        Long sessionId = findSessionId(user.getSession().getSessionValue());
        // If username or email is taken, throw error
        checkUsernameOrEmailExists(user.getUsername(), user.getEmail());
        String uuid = Utility.getUuid();
        user.setUuid(uuid);
        // Create user & update user id in session table
        createUserAndUpdateSession(user, sessionId);

        return getJwtToken(uuid, user.getSession().getSessionValue());
    }

    public JwtAuthenticationResponse login(User user){
        validateLoginRequest(user);
        Long sessionId = findSessionId(user.getSession().getSessionValue());
        User existingUser = userRepository.findPasswordByUserIdOrEmail(user.getUsernameOrEmail());
        if(existingUser == null) throw new ResourceNotFoundException(TWTR10006);
        if(!PasswordUtility.checkPass(user.getPassword(), existingUser.getPasswordHash())) throw new ResourceNotFoundException(TWTR10006);
        sessionService.updateUserId(sessionId, existingUser.getId());
        return getJwtToken(existingUser.getUuid(), user.getSession().getSessionValue());
    }

    public void deleteToken(Long sessionId){
        sessionService.invalidateSession(sessionId);
    }

    private Long findSessionId(String sessionValue){
        Long sessionId = sessionService.findIdBySessionValue(sessionValue);
        // If invalid session throw error
        if(sessionId == null) throw new InvalidArgumentException(TWTR10002);
        return sessionId;
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

    private void validateLoginRequest(User user){
        if(StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getUsernameOrEmail())){
            throw new InvalidArgumentException(TWTR10004);
        }
        if(Objects.isNull(user.getSession()) || StringUtils.isEmpty(user.getSession().getSessionValue())){
            throw new InvalidArgumentException(TWTR10005);
        }
    }

}
