package br.com.sellmanager.persistence;

import br.com.sellmanager.model.sell.SellByReservation;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface SellByReservationPersistence extends CrudRepository<SellByReservation, UUID> {
}
