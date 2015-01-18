package com.rotterdam.controllers.auth;


import javax.inject.Named;
import java.math.BigInteger;
import java.security.SecureRandom;

/**
 */
@Named
public class SessionIdentifierGenerator {
    private SecureRandom random = new SecureRandom();

    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }
}
