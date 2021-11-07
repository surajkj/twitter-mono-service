package com.twitter;

import net.sf.uadetector.*;
import net.sf.uadetector.service.UADetectorServiceFactory;

public class Test {

    public static UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();

    public static void main(String... args){
        String googleBot21 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36";
        printUa(parser.parse(googleBot21));
    }

    public static void printUa(ReadableUserAgent agent){
        System.out.println("- - - - - - - - - - - - - - - - -");
        // type
        System.out.println("Browser type: " + agent.getType().getName());
        System.out.println("Browser name: " + agent.getName());
        VersionNumber browserVersion = agent.getVersionNumber();
        System.out.println("Browser version: " + browserVersion.toVersionString());
        System.out.println("Browser version major: " + browserVersion.getMajor());
        System.out.println("Browser version minor: " + browserVersion.getMinor());
        System.out.println("Browser version bug fix: " + browserVersion.getBugfix());
        System.out.println("Browser version extension: " + browserVersion.getExtension());
        System.out.println("Browser producer: " + agent.getProducer());

        // operating system
        OperatingSystem os = agent.getOperatingSystem();
        System.out.println("\nOS Name: " + os.getName());
        System.out.println("OS Producer: " + os.getProducer());
        VersionNumber osVersion = os.getVersionNumber();
        System.out.println("OS version: " + osVersion.toVersionString());
        System.out.println("OS version major: " + osVersion.getMajor());
        System.out.println("OS version minor: " + osVersion.getMinor());
        System.out.println("OS version bug fix: " + osVersion.getBugfix());
        System.out.println("OS version extension: " + osVersion.getExtension());

        // device category
        ReadableDeviceCategory device = agent.getDeviceCategory();
        System.out.println("\nDevice: " + device.getName());

    }
}