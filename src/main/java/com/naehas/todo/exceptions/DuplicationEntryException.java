package com.naehas.todo.exceptions;

public class DuplicationEntryException extends Exception {

    public DuplicationEntryException() {
    }

    public DuplicationEntryException(String message) {
        super(message);
    }
}
