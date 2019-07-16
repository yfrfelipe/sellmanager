package br.com.sellmanager.exception.southbound.Product;

import br.com.sellmanager.exception.southbound.AbstractRetrievereException;

public class ProductNotFoundException extends AbstractRetrievereException {

    public ProductNotFoundException(final String message) {
        super(message);
    }
}
