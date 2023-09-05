package ru.kata.spring.boot_security.demo.web.exeptions;

public class UserDeleteException extends Exception {
    public UserDeleteException(String message) {
        super(message);
    }
}
