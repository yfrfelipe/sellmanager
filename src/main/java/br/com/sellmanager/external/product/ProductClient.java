package br.com.sellmanager.external.product;

import com.google.common.collect.Maps;
import java.util.Map;
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

    @Override
    public void getProduct() {
        LOG.info("Setting product down in stock");
        final Map<Integer, Integer> body = Maps.newHashMap();
        body.put(5, 10);
        WebClient.builder().build().put()
                .uri("http://localhost:8080/product/down")
                .body(Mono.just(body), Map.class)
                .retrieve()
                .bodyToMono(String.class).subscribe(this);
    }

    @Override
    public void accept(final String accept) {
        LOG.info(accept);
    }
}
