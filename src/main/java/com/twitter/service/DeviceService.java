package com.twitter.service;

import com.twitter.dto.Device;
import com.twitter.repository.DeviceRepository;
import com.twitter.utility.UserAgentParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeviceService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository){
        this.deviceRepository = deviceRepository;
    }

    public Long createDevice(String userAgent){
        Device device = UserAgentParser.getDeviceFromUserAgent(userAgent);
        return deviceRepository.createDevice(device);
    }

}
