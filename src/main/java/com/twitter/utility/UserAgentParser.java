package com.twitter.utility;

import com.twitter.dto.Device;
import lombok.extern.slf4j.Slf4j;
import net.sf.uadetector.OperatingSystem;
import net.sf.uadetector.ReadableDeviceCategory;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

/**
 * Parse device information from userAgent
 * Ref:- https://memorynotfound.com/parse-user-agent-java/
 */
@Slf4j
public class UserAgentParser {

    private static final UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();

    public static Device getDeviceFromUserAgent(String userAgent){
        try {
            Device device = mapDevice(decodeUserAgent(userAgent));
            device.setUserAgent(userAgent);
            return device;
        }catch (Exception e){
            log.error("Exception while parsing user agent ", e);
            return Device
                    .builder()
                    .userAgent(userAgent)
                    .build();
        }
    }

    private static ReadableUserAgent decodeUserAgent(String userAgent){
        return parser.parse(userAgent);
    }

    private static Device mapDevice(ReadableUserAgent agent){
        OperatingSystem os = agent.getOperatingSystem();
        ReadableDeviceCategory device = agent.getDeviceCategory();
        return Device
                .builder()
                .agentType(agent.getType().getName())
                .agentName(agent.getName())
                .agentVersion(agent.getVersionNumber().toVersionString())
                .agentProducer(agent.getProducer())
                .os(os.getName())
                .osProducer(os.getProducer())
                .osVersion(os.getVersionNumber().toVersionString())
                .deviceType(device.getName())
                .build();
    }
}
