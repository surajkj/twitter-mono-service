package com.twitter.service;

import com.twitter.exception.InvalidArgumentException;
import com.twitter.repository.UserRepository;
import com.twitter.dto.User;
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

    @Autowired
    public UserService(UserRepository userRepository,
                       SessionService sessionService) {
        this.userRepository = userRepository;
        this.sessionService = sessionService;
    }

    public User createUser(User user) {
        Long sessionId = sessionService.findIdBySessionValue(user.getSession().getSessionValue());
        // If invalid session throw error
        if(sessionId == null) throw new InvalidArgumentException(TWTR10002);

        // If username or email is taken, throw error
        if(userRepository.checkUsernameOrEmailExists(user.getUsername(), user.getEmail())) throw new InvalidArgumentException(TWTR10003);

        user.setPasswordHash(PasswordUtility.hashPassword(user.getPassword()));
        user.setUuid(Utility.getUuid());
        Long userId = userRepository.createUser(user);
        log.info("saved user with id {} ", userId);

        sessionService.updateUserId(sessionId, userId);

        return User.builder().id(userId).build();
    }


}
