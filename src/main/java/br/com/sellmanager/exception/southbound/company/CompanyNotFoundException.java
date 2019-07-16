package br.com.sellmanager.exception.southbound.company;

import br.com.sellmanager.exception.southbound.AbstractRetrievereException;

public class CompanyNotFoundException extends AbstractRetrievereException {

    public CompanyNotFoundException(final String message) {
        super(message);
    }
}
