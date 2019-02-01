package br.com.sellmanager.external.product;

import java.util.Map;
import java.util.UUID;

public interface ProductClientService {

    /**
     * Start a new reservation by sending a PUT request to StoreManager,
     * so, the StoreManager will record that ID in order to manage the
     * products reservation for a given sell. It will avoid sell the
     * same item twice and will lock a given item for a configurable idle time..
     * @param reservationID, the reservation ID.
     * @param productByQuantity, a {@link Map} containing the Product ID by quantity.
     */
    void reserve(UUID reservationID, Map<Integer, Integer> productByQuantity);

    /**
     * Send a PUT request to StoreManager in order to cancel a given reservation.
     * @param reservationID, the reservation ID.
     */
    void cancelReservation(UUID reservationID);

    /**
     * Send a PUT request to StoreManager in order to finalized a given reservation.
     * @param reservationID, the reservation ID.
     */
    void finalizeReservation(UUID reservationID);
}
