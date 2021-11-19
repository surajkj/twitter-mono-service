package com.twitter.security;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtility {

    private PasswordUtility(){
    }

    public static String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public static boolean checkPass(String plainPassword,
                              String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

}
