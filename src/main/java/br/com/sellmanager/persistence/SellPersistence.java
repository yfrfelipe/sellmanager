package br.com.sellmanager.persistence;


import br.com.sellmanager.model.sell.Sell;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SellPersistence extends CrudRepository<Sell, Integer>, PagingAndSortingRepository<Sell, Integer> {
}
