package com.au.iress.toy.robot.exception;

public class ToyException extends Exception {

    public ToyException(String string) {
        super(string);
    }

    public ToyException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
