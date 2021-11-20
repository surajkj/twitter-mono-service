package com.twitter.security;

import com.twitter.dto.User;
import com.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    UserRepository userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadUserByUUID(String uuid,
                                      List<String> teamRoles) throws UsernameNotFoundException {
        User user = userDao.findByUuid(uuid);
        if(user != null){
            CustomUserDetails userDetails = CustomUserDetails
                    .builder()
                    .uuid(uuid)
                    .id(user.getId())
                    .build();
            List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
            for(String roles : teamRoles){
                simpleGrantedAuthorities.add(new SimpleGrantedAuthority(roles));
            }
            userDetails.setAuthorities(simpleGrantedAuthorities);
            return userDetails;
        }
        throw new UsernameNotFoundException("User not found");
    }

}
