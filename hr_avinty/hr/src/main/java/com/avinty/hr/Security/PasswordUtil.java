package com.avinty.hr.Security;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class PasswordUtil {

    public boolean validatePassword(String userPassword, String passwordInput) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return userPassword.equals(generateSHA(passwordInput));
    }


    public static String generateSHA(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.reset();
        byte[] buffer = input.getBytes(StandardCharsets.UTF_8);
        messageDigest.update(buffer);
        byte[] digest = messageDigest.digest();
        StringBuilder hexStr = new StringBuilder();
        for (byte b : digest) {
            hexStr.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return hexStr.toString();
    }

}
