package com.twitter.service;

import com.twitter.database.repository.UserRepository;
import com.twitter.dto.User;
import com.twitter.security.PasswordUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        user.setPasswordHash(PasswordUtility.hashPassword(user.getPassword()));
       Long userId = userRepository.createUser(user);
       log.info("saved user with id {} ", userId);
       return User.builder().id(userId).build();
    }
}
