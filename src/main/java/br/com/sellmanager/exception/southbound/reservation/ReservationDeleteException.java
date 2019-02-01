package br.com.sellmanager.exception.southbound.reservation;

import br.com.sellmanager.exception.southbound.AbstractDeleteException;

public class ReservationDeleteException extends AbstractDeleteException {

    public ReservationDeleteException(String message) {
        super(message);
    }
}
