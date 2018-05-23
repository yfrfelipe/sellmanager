package br.com.sellmanager.external.product;

import java.util.Map;
import java.util.UUID;

public interface ProductClientService {

    void reserve(UUID reservationID, Map<Integer, Integer> productByQuantity);

    void cancelReservation(UUID reservationID);

    void finalizeReservation(UUID reservationID);
}
