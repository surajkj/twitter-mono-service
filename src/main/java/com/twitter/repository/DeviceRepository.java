package com.twitter.repository;

import com.twitter.dto.Device;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface DeviceRepository {

    @SqlUpdate("insert into device (agent_type, agent_name, agent_producer, agent_version, os, os_producer, os_version, device_type, user_agent) " +
            "values(:agentType, :agentName, :agentProducer, :agentVersion, :os, :osProducer, :osVersion, :deviceType, :userAgent);")
    @GetGeneratedKeys
    Long createDevice(@BindBean Device device);

}
