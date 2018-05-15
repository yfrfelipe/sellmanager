package br.com.sellmanager.exception.southbound;

public abstract class AbstractCreateException extends RuntimeException {

    public AbstractCreateException(final String message) {
        super("[CREATE EXCEPTION] " + message);
    }
}
