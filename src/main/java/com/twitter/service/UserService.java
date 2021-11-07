package com.twitter.service;

import com.twitter.repository.UserRepository;
import com.twitter.dto.User;
import com.twitter.security.PasswordUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public User createUser(User user) throws Exception {
        Long sessionId = sessionService.findIdBySessionValue(user.getSession().getSessionValue());
        // If invalid session throw error
        if(sessionId == null) throw new Exception("Invalid session");

        // If username or email is taken, throw error
        if(userRepository.checkUsernameOrEmailExists(user.getUsername(), user.getEmail())) throw new Exception("Username or email already exists");

        user.setPasswordHash(PasswordUtility.hashPassword(user.getPassword()));
        Long userId = userRepository.createUser(user);
        log.info("saved user with id {} ", userId);

        sessionService.updateUserId(sessionId, userId);

        return User.builder().id(userId).build();
    }


}
