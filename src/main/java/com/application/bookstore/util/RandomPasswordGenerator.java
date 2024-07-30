package com.application.bookstore.util;

import java.security.SecureRandom;
import java.util.Base64;

public class RandomPasswordGenerator {
    private static final Integer ARRAY_LENGTH = 24;

    public String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[ARRAY_LENGTH];
        random.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }
}
