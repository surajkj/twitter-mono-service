package com.twitter.repository;

import com.twitter.dto.Session;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface SessionRepository {

    @SqlUpdate("insert into session (session_value, device_id) values (:sessionValue, :deviceId);")
    @GetGeneratedKeys
    Long createSession(@BindBean Session session);

}
