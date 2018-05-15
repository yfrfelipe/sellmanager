package br.com.sellmanager.service.converter;


import br.com.sellmanager.dto.AbstractDTO;
import br.com.sellmanager.model.AbstractEntity;

public interface ConverterService<E extends AbstractEntity, D extends AbstractDTO> {

    E fromDto(D dto);

    D toDto(E entity);
}
