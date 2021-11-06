package com.twitter.database.repository;


import com.twitter.database.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long> {


}
