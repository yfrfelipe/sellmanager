package br.com.sellmanager.exception.southbound.Product;

import br.com.sellmanager.exception.southbound.AbstractUpdateException;

public class ProductUpdateException extends AbstractUpdateException {

    public ProductUpdateException(final String message) {
        super(message);
    }
}
