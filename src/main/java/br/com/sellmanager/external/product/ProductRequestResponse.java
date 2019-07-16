package br.com.sellmanager.external.product;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

public class ProductRequestResponse {

    @Getter
    @Setter
    private UUID id;
    @Getter
    @Setter
    private String message;
}
