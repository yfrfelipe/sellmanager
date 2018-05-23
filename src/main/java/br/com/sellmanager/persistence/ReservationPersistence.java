package br.com.sellmanager.persistence;

import br.com.sellmanager.model.reservation.Reservation;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface ReservationPersistence extends CrudRepository<Reservation, UUID>{
}
