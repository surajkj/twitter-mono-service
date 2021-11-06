package com.twitter.service;

import com.twitter.database.entity.Users;
import com.twitter.database.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(Users user){
       Long l = userRepository.createUser(user);
       log.info("saves data {} ", l);
    }
}
