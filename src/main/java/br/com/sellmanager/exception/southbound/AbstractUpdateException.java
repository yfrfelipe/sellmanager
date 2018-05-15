package br.com.sellmanager.exception.southbound;

public abstract class AbstractUpdateException extends RuntimeException {

    public AbstractUpdateException(final String message) {
        super("[UPDATE EXCEPTION] " + message);
    }
}
