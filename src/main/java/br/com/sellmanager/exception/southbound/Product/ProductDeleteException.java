package br.com.sellmanager.exception.southbound.Product;

import br.com.sellmanager.exception.southbound.AbstractDeleteException;

public class ProductDeleteException extends AbstractDeleteException {

    public ProductDeleteException(final String message) {
        super(message);
    }
}
