package br.com.sellmanager.exception.southbound;

public abstract class AbstractDeleteException extends RuntimeException {

    public AbstractDeleteException(final String message) {
        super("[DELETE EXCEPTION] " + message);
    }
}
