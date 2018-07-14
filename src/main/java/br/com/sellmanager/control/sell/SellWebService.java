package br.com.sellmanager.control.sell;

import br.com.sellmanager.control.AbstractWebService;
import br.com.sellmanager.dto.sell.SellDTO;
import br.com.sellmanager.dto.sell.SellPageDTO;

public interface SellWebService extends AbstractWebService<SellDTO> {

    SellPageDTO listByQuantity(Integer quantity);
}
