package com.twitter.database.repository;

import com.twitter.dto.User;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface UserRepository {

    @SqlUpdate("insert into users(username, name, email, password_hash) values (:username, :name, :email, :passwordHash);")
    @GetGeneratedKeys
    Long createUser(@BindBean User user);
}
