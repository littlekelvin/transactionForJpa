package com.kelvin.oocl.exception;

public class TransactionCommitException extends RuntimeException{

    public TransactionCommitException(String message) {
        super(message);
    }
}
