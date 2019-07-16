package br.com.sellmanager.exception.southbound.Product;

import br.com.sellmanager.exception.southbound.AbstractCreateException;

public class ProductCreateException extends AbstractCreateException {

    public ProductCreateException(final String message) {
        super(message);
    }
}
