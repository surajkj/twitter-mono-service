package com.twitter.database.repository;


import com.twitter.database.entity.Users;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface UserRepository {

    @SqlUpdate("insert into users(username, name, email) values (:username, :name, :email);")
    @GetGeneratedKeys
    Long createUser(@BindBean Users user);
}
