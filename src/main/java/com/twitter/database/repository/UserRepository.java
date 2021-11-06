package com.twitter.database.repository;


import com.twitter.database.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {


}
