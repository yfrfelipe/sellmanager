package br.com.sellmanager.persistence;


import br.com.sellmanager.model.sell.Sell;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SellPersistence extends CrudRepository<Sell, Integer>, PagingAndSortingRepository<Sell, Integer> {
    Page<Sell> findSellByTimestamp(Long timestamp, Pageable pageable);
}
