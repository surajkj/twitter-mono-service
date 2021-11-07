package com.twitter.repository;

import com.twitter.dto.Session;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface SessionRepository {

    @SqlUpdate("insert into session (session_value, device_id, ip_address) values (:sessionValue, :deviceId, :ipAddress);")
    @GetGeneratedKeys
    Long createSession(@BindBean Session session);

}
