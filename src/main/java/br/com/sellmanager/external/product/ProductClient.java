package br.com.sellmanager.external.product;

import br.com.sellmanager.exception.southbound.reservation.ReservationFinalizeException;
import br.com.sellmanager.exception.southbound.reservation.ReservationNotFoundException;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

//TODO: WIP
@Service
public class ProductClient implements ProductClientService {
    private static final Logger LOG = LogManager.getLogger(ProductClient.class);

    //TODO: Change the URI IP address
    private static final String BASIC_URI = "http://localhost:8080";

    @Override
    public void reserve(final UUID reservationID, final Map<Integer, Integer> productByQuantity,
                        final Consumer<String> consumer) {
        sendPutRequest(reservationID, "/reserve", consumer);
    }

    @Override
    public void cancelReservation(final UUID reservationID, final Consumer<String> consumer) {
        sendPutRequest(reservationID, "/reservation/cancel", consumer);
    }

    @Override
    public void finalizeReservation(final UUID reservationID, final Consumer<String> consumer) throws ReservationFinalizeException {
        sendPutRequest(reservationID, "/reservation/finalize", consumer);
    }

    public void getProduct() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append(BASIC_URI);
        buffer.append("/down");

        final Map<Integer, Integer> body = Maps.newHashMap();
        body.put(5, 1);
        WebClient.ResponseSpec respSpec = WebClient.builder().build().put()
                .uri(buffer.toString())
                .body(Mono.just(body), Map.class)
                .retrieve();

    }

    private synchronized void sendPutRequest(final UUID reservationID,
                                             final String service,
                                             final Consumer<String> consumer)
            throws ReservationNotFoundException {
        final StringBuffer buffer = new StringBuffer();
        buffer.append(BASIC_URI);
        buffer.append(service);

        final WebClient.ResponseSpec respSpec = WebClient.builder().build().put()
                .uri(buffer.toString())
                .body(Mono.just(reservationID), UUID.class)
                .retrieve();
        respSpec.bodyToMono(String.class).subscribe(consumer);
        respSpec.onStatus(httpStatus -> (httpStatus.equals(HttpStatus.NOT_FOUND)),
                clientResponse -> {
                    return Mono.empty();
                });
        respSpec.onStatus(httpStatus -> (httpStatus.equals(HttpStatus.OK)),
                clientResponse -> {
                    return Mono.empty();
                });
    }
}
