package ru.nmt.strategy;

import ru.nmt.interfaces.LoginStrategy;

public class LoginContext {

    private LoginStrategy strategy;

    public void setStrategy(LoginStrategy strategy) {
        this.strategy = strategy;
    }

    public void login(String userId, String password) {
        strategy.login(userId, password);
    }
}
