package br.com.sellmanager.control;

import br.com.sellmanager.dto.AbstractDTO;

public interface AbstractWebService <T extends AbstractDTO, E extends AbstractDTO, I> extends AutoCloseable {

    /**
     * This method is responsible to receive a DTO to be created.
     */
    void post(T modelDTO);

    /**
     * This method is responsble to retrieve a given data.
     */
    E get(I id);

    void put(I id, T modelDTO);

    void delete(I id);
}
