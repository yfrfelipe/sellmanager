package br.com.sellmanager.exception.southbound.company;

import br.com.sellmanager.exception.southbound.AbstractUpdateException;

public class CompanyUpdateException extends AbstractUpdateException {

    public CompanyUpdateException(final String message) {
        super(message);
    }
}
