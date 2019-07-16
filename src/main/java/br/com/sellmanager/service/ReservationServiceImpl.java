package br.com.sellmanager.service;

import br.com.sellmanager.exception.southbound.reservation.ReservationNotFoundException;
import br.com.sellmanager.exception.southbound.reservation.ReservationUpdateException;
import br.com.sellmanager.external.product.ProductClientService;
import br.com.sellmanager.model.reservation.Reservation;
import br.com.sellmanager.persistence.ReservationPersistence;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService, Consumer<String> {
    final Logger LOG = LogManager.getLogger(ReservationServiceImpl.class);

    @Autowired
    private ReservationPersistence reservationPersistence;

    @Autowired
    private ProductClientService productClientService;

    @Override
    public UUID createTransaction() {
        final UUID transactionID = UUID.randomUUID();
        final Reservation reservation = new Reservation();
        reservation.setTransactionID(transactionID);
        reservation.setTimestamp(System.currentTimeMillis());

        reservationPersistence.save(reservation);
        return transactionID;
    }

    @Override
    public void reserveItems(final UUID transactionID,
                             final Map<Integer, Integer> productByQuantity) throws ReservationUpdateException {
        productClientService.reserve(transactionID, productByQuantity, this);
    }

    @Override
    public void cancelReservation(final UUID transactionID) {
        productClientService.cancelReservation(transactionID, this);
    }

    @Override
    public void finalizeTransaction(final UUID transactionID) throws ReservationNotFoundException {
        productClientService.finalizeReservation(transactionID, this);
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public void accept(final String accept) {
        LOG.info("Reservation result: {}", accept);
    }
}
