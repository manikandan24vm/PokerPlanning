package com.poker.planning.exceptionhandling;

public class UserStoryNotFoundException extends RuntimeException {
    public UserStoryNotFoundException(String message) {
        super(message);
    }
}
