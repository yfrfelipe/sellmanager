package br.com.sellmanager.exception.southbound.reservation;

import br.com.sellmanager.exception.southbound.AbstractCreateException;

public class ReservationCreateException extends AbstractCreateException {

    public ReservationCreateException(String message) {
        super(message);
    }
}
