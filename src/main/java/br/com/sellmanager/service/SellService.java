package br.com.sellmanager.service;

import br.com.sellmanager.dto.sell.SellDTO;
import br.com.sellmanager.exception.southbound.sell.SellCreateException;
import br.com.sellmanager.exception.southbound.sell.SellDeleteException;
import br.com.sellmanager.exception.southbound.sell.SellNotFoundException;
import br.com.sellmanager.exception.southbound.sell.SellUpdateException;

public interface SellService extends AbstractService<
        SellDTO,
        SellCreateException,
        SellUpdateException,
        SellNotFoundException,
        SellDeleteException> {
}
