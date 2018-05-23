package br.com.sellmanager.exception.southbound.reservation;

import br.com.sellmanager.exception.southbound.AbstractRetrievereException;

public class ReservationNotFoundException extends AbstractRetrievereException {

    public ReservationNotFoundException(String message) {
        super(message);
    }
}
