package com.ecdtms.model.auth;

import com.ecdtms.model.personnel.Employee;

public class UserSession {

    private static UserSession instance;
    private Employee currentUser;

    private UserSession() {}

    public static synchronized UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void login(Employee user) {
        this.currentUser = user;
    }

    public void logout() {
        this.currentUser = null;
    }

    public Employee getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
}