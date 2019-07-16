package br.com.sellmanager.exception.southbound.reservation;

import br.com.sellmanager.exception.southbound.AbstractUpdateException;

public class ReservationFinalizeException extends AbstractUpdateException {
    public ReservationFinalizeException(final String message) {
        super(message);
    }
}
