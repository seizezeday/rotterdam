package com.rotterdam.tools;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Decoder {

    private static MessageDigest md;

    public static String encryptPass(String pass){
        try {
            byte[] passDigestBytes = getDigestBytes(pass);
            return getStringCode(passDigestBytes);
        }catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private static String getStringCode(byte[] passDigestBytes) {
        StringBuffer passDigestString = new StringBuffer();
        for (int i = 0; i < passDigestBytes.length; i++) {
            passDigestString.append(Integer.toHexString(0xff & passDigestBytes[i]));
        }
        return passDigestString.toString();
    }

    private static byte[] getDigestBytes(String pass) throws NoSuchAlgorithmException {
        md = MessageDigest.getInstance("MD5");
        md.reset();
        byte[] passBytes = pass.getBytes();
        return md.digest(passBytes);
    }
}

