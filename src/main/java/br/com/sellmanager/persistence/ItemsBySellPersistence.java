package br.com.sellmanager.persistence;

import br.com.sellmanager.model.sell.ItemsBySell;
import org.springframework.data.repository.CrudRepository;

public interface ItemsBySellPersistence extends CrudRepository<ItemsBySell, Integer> {
}
