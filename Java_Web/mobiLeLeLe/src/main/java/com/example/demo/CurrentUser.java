package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private String userName;
    private String name;
    private boolean loggedIn;

    public String getUserName() {
        return userName;
    }

    public CurrentUser setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getName() {
        return name;
    }

    public CurrentUser setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public CurrentUser setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
        return this;
    }

    public boolean isAnonymous() {
        return !isLoggedIn();
    }

    public void clear() {
        setLoggedIn(false);
        setName(null);
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", loggedIn=" + loggedIn +
                '}';
    }
}
