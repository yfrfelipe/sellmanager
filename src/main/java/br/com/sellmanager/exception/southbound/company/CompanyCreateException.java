package br.com.sellmanager.exception.southbound.company;

import br.com.sellmanager.exception.southbound.AbstractCreateException;

public class CompanyCreateException extends AbstractCreateException {

    public CompanyCreateException(final String message) {
        super(message);
    }
}
