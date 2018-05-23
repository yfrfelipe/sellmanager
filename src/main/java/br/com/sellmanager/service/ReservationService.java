package br.com.sellmanager.service;

import br.com.sellmanager.exception.southbound.reservation.ReservationCreateException;
import br.com.sellmanager.exception.southbound.reservation.ReservationUpdateException;
import java.util.Map;
import java.util.UUID;

public interface ReservationService extends AutoCloseable {

    UUID createTransaction() throws ReservationCreateException;

    void reserveItems(UUID transactionID, Map<Integer, Integer> productByQuantity) throws ReservationUpdateException;

    void cancelReservation(UUID transactionID) throws ReservationUpdateException;

    void finalizeTransaction(UUID transactionID) throws ReservationUpdateException;
}
