package br.com.sellmanager.exception.northbound;

public class AbstractNorthBoundException extends RuntimeException {

    public AbstractNorthBoundException(final String message) {
        super("[NorthBound Exception] " + message);
    }
}
