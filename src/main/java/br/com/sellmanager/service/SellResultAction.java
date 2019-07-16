package br.com.sellmanager.service;

import br.com.sellmanager.exception.southbound.reservation.ReservationFinalizeException;
import java.util.UUID;

public interface SellResultAction {
    void onSuccess(UUID reservationID);

    void onFail(String message, UUID reservationID) throws ReservationFinalizeException;
}
