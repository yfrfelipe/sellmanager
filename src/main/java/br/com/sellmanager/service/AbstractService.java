package br.com.sellmanager.service;

import br.com.sellmanager.dto.AbstractDTO;
import br.com.sellmanager.exception.southbound.AbstractCreateException;
import br.com.sellmanager.exception.southbound.AbstractDeleteException;
import br.com.sellmanager.exception.southbound.AbstractRetrievereException;

public interface AbstractService<
        T extends AbstractDTO,
        E extends AbstractDTO,
        I,
        C extends AbstractCreateException,
        R extends AbstractRetrievereException,
        D extends AbstractDeleteException> extends AutoCloseable {

    T create(T entityDTO) throws C;

    E retrieve(I id) throws R;

    void delete(I id) throws D;
}
