package com.twitter.repository;

import com.twitter.dto.Session;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface SessionRepository {

    @SqlUpdate("insert into session (session_value, device_id, ip_address) values (:sessionValue, :deviceId, :ipAddress);")
    @GetGeneratedKeys
    Long createSession(@BindBean Session session);

    // Checking null user id, because if it is not null mean's session belongs to different user
    @SqlQuery("select id from session where session_value = :sessionValue and user_id is null and is_active=true;")
    Long findFreshIdBySessionValue(String sessionValue);

    @SqlQuery("select id from session where session_value = :sessionValue and is_active=true;")
    Long findIdBySessionValue(String sessionValue);

    @SqlUpdate("update session set user_id = :userId, updated_time=current_timestamp where id = :sessionId;")
    void updateUserId(Long sessionId,
                      Long userId);

    @SqlUpdate("update session set is_active = false, updated_time=current_timestamp where id = :sessionId;")
    void invalidateSession(Long sessionId);
}
