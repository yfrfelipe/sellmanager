package br.com.sellmanager.service;

import br.com.sellmanager.dto.sell.SellDTO;
import br.com.sellmanager.exception.southbound.sell.SellCreateException;
import br.com.sellmanager.exception.southbound.sell.SellDeleteException;
import br.com.sellmanager.exception.southbound.sell.SellNotFoundException;
import br.com.sellmanager.exception.southbound.sell.SellUpdateException;
import br.com.sellmanager.external.product.ProductClientService;
import br.com.sellmanager.persistence.SellPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellServiceImpl implements SellService {

    @Autowired
    private SellPersistence sellPersistence;

    @Autowired
    private ProductClientService productClientService;

    @Override
    public void create(SellDTO entityDTO) throws SellCreateException {
        
    }

    @Override
    public SellDTO retrieve(Integer id) throws SellNotFoundException {
        return null;
    }

    @Override
    public void update(Integer id, SellDTO entityDTO) throws SellUpdateException {

    }

    @Override
    public void delete(Integer id) throws SellDeleteException {

    }

    @Override
    public void close() throws Exception {

    }
}
