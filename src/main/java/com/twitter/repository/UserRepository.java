package com.twitter.repository;

import com.twitter.dto.User;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface UserRepository {

    @SqlUpdate("insert into users(username, name, email, password_hash) values (:username, :name, :email, :passwordHash);")
    @GetGeneratedKeys
    Long createUser(@BindBean User user);

    @SqlQuery("select exists(select id from users where username = :username or email= :email);")
    boolean checkUsernameOrEmailExists(String username,
                                       String email);
}
