package br.com.sellmanager.exception.southbound.reservation;

import br.com.sellmanager.exception.southbound.AbstractUpdateException;

public class ReservationUpdateException extends AbstractUpdateException {

    public ReservationUpdateException(String message) {
        super(message);
    }
}
