package br.com.sellmanager.exception.southbound.company;

import br.com.sellmanager.exception.southbound.AbstractDeleteException;

public class CompanyDeleteException extends AbstractDeleteException {

    public CompanyDeleteException(final String message) {
        super(message);
    }
}
