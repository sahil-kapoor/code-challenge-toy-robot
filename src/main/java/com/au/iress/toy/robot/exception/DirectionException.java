package com.au.iress.toy.robot.exception;

public class DirectionException extends Exception {

    public DirectionException(String string) {
        super(string);
    }

    public DirectionException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
