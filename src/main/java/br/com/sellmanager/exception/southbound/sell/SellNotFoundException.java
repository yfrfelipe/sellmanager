package br.com.sellmanager.exception.southbound.sell;

import br.com.sellmanager.exception.southbound.AbstractRetrievereException;

public class SellNotFoundException extends AbstractRetrievereException {

    public SellNotFoundException(String message) {
        super(message);
    }
}
