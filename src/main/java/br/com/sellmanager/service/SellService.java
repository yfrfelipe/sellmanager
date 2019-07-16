package br.com.sellmanager.service;

import br.com.sellmanager.dto.sell.SellDTO;
import br.com.sellmanager.dto.sell.SellInProgressDTO;
import br.com.sellmanager.dto.sell.SellPageDTO;
import br.com.sellmanager.exception.northbound.SellFinalizeException;
import br.com.sellmanager.exception.southbound.sell.SellCreateException;
import br.com.sellmanager.exception.southbound.sell.SellDeleteException;
import br.com.sellmanager.exception.southbound.sell.SellNotFoundException;
import br.com.sellmanager.exception.southbound.sell.SellUpdateException;
import java.util.UUID;
import java.util.function.Consumer;

public interface SellService extends AbstractService<
        SellDTO,
        SellInProgressDTO,
        Integer,
        SellCreateException,
        SellNotFoundException,
        SellDeleteException> {

    void finalizeSell(Integer id, Consumer<String> consumer) throws SellFinalizeException;

    void cancelSell(Integer id, Consumer<String> consumer) throws SellUpdateException;

    SellPageDTO listByQuantity(Integer quantity, Integer pageNum);

    SellPageDTO listBy(String timestamp, Integer quantity, Integer pageNum);

    SellResultAction getResultAction(UUID reservationID);
}
