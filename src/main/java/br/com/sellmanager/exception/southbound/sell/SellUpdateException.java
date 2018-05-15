package br.com.sellmanager.exception.southbound.sell;

import br.com.sellmanager.exception.southbound.AbstractUpdateException;

public class SellUpdateException extends AbstractUpdateException {

    public SellUpdateException(String message) {
        super(message);
    }
}
