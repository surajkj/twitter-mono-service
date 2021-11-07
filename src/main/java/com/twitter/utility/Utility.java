package com.twitter.utility;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class Utility {

    public static String generateRandomString(int length){
        String alphaNumericString = "ABCDEFGHIJKLMNPQRSTUVWXYZ"
                + "123456789";
        return generateRandomSalt(length, alphaNumericString);
    }

    public static String generateRandomNumber(int length){
        String alphaNumericString = "123456789";
        return generateRandomSalt(length, alphaNumericString);
    }

    private static String generateRandomSalt(int length,
                                             String salt){
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int)(salt.length() * Math.random());
            // add Character one by one in end of sb
            sb.append(salt.charAt(index));
        }
        return sb.toString();
    }

    public static String getUuid(){
        return UUID.randomUUID().toString();
    }

}
