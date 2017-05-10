package com.ksoft.kdm.config;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserLoginPasswordEncoder extends Md5PasswordEncoder {
    @Override
    public String encodePassword(String input, Object salt) {
        input = input.toLowerCase();
        try {
            int middle = input.length() / 2;
            byte[] result = MessageDigest.getInstance("MD5").digest((input.substring(0, middle) + salt + input.substring(middle)).getBytes());
            StringBuilder strBuilder = new StringBuilder(result.length * 2);
            for (byte b : result) {
                String s = Integer.toHexString(b & 0x00FF);
                if (1 == s.length()) {
                    strBuilder.append('0');
                }
                strBuilder.append(s);
            }
            return strBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
        return super.isPasswordValid(encPass, rawPass, salt);
    }

    /*public static void main(String[] args) {
        UserLoginPasswordEncoder encoder = new UserLoginPasswordEncoder();
        String rw = "e10adc3949ba59abbe56e057f20f883e";
        String en = "3ea6118128fcd04683b77dac590e4a55";
        String salt = "123123123";
        System.out.println(encoder.isPasswordValid(en, rw, salt));
        System.out.println(encoder.encodePassword(rw, salt));
    }*/
}
