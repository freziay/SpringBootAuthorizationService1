package ru.netology.springbootauthorizationservicenew.exception;

public class UnauthorizedUser extends RuntimeException {
    public UnauthorizedUser(String msg) {
        super(msg);
    }

}
