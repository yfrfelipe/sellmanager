package br.com.sellmanager.model.sell;

import br.com.sellmanager.model.AbstractEntity;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.hibernate.annotations.Type;

@Entity(name = "tb_sell_by_reservation")
@Data
@EqualsAndHashCode
public class SellByReservation extends AbstractEntity {

    @Id
    @Getter
    @Type(type = "uuid-char")
    @Column(nullable = false, updatable = false)
    @NonNull
    private UUID reservationID;
    @Getter
    @Column(nullable = false, updatable = false)
    @NonNull
    private Integer sellID;
}
