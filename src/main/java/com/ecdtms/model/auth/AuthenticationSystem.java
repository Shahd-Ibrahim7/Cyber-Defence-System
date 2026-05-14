package com.ecdtms.model.auth;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationSystem {

    private Map<String, String> userCredentials;

    public AuthenticationSystem() {
        userCredentials = new HashMap<>();
        userCredentials.put("admin@ecdtms.com", "admin123");
    }

    public boolean authenticate(String email, String password) {

        if (email == null || password == null) {
            return false;
        }

        if (userCredentials.containsKey(email)) {
            return userCredentials.get(email).equals(password);
        }

        return false;
    }
}