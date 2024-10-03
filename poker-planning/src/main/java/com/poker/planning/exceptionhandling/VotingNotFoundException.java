package com.poker.planning.exceptionhandling;

public class VotingNotFoundException extends RuntimeException {
    public VotingNotFoundException(String message) {
        super(message);
    }
}
