package br.com.sellmanager.external.product;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

//TODO: WIP
@Service
public class ProductClient implements ProductClientService, Consumer<String> {
    private static final Logger LOG = LogManager.getLogger(ProductClient.class);

    //TODO: Change the URI IP address
    private static final String BASIC_URI = "http://localhost:8080/product/";

    @Override
    public void reserve(final UUID reservationID, final Map<Integer, Integer> productByQuantity) {
        sendPutRequest(reservationID, "/reserve");
    }

    @Override
    public void cancelReservation(final UUID reservationID) {
        sendPutRequest(reservationID, "/reservation/cancel");
    }

    @Override
    public void finalizeReservation(final UUID reservationID) {
        sendPutRequest(reservationID, "/reservation/finalize");
    }

    public void getProduct() {
//        LOG.info("Setting product down in stock");
        final StringBuffer buffer = new StringBuffer();
        buffer.append(BASIC_URI);
        buffer.append("/down");

        final Map<Integer, Integer> body = Maps.newHashMap();
        body.put(5, 1);
        WebClient.builder().build().put()
                .uri(buffer.toString())
                .body(Mono.just(body), Map.class)
                .retrieve()
                .bodyToMono(String.class).subscribe(this);
    }

    private synchronized void sendPutRequest(final UUID reservationID, final String service) {
        final StringBuffer buffer = new StringBuffer();
        buffer.append(BASIC_URI);
        buffer.append(service);

        WebClient.builder().build().put()
                .uri(buffer.toString())
                .body(Mono.just(reservationID), UUID.class)
                .retrieve()
                .bodyToMono(String.class).subscribe(this);
    }

    @Override
    public void accept(final String accept) {
        LOG.info(accept);
    }
}
