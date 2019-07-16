package br.com.sellmanager.persistence;

import br.com.sellmanager.model.sell.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemsPersistence  extends CrudRepository<Item, Integer> {
}
