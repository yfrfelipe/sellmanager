package br.com.sellmanager.model.sell;

import br.com.sellmanager.model.AbstractEntity;
import br.com.sellmanager.model.reservation.Reservation;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Type;

@Entity(name = "tb_sell")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Sell extends AbstractEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sell_gen", sequenceName = "sell_seq")
    @Column(nullable = false, updatable = false)
    @NonNull
    private Integer id;

    @Getter
    @Column(nullable = false, updatable = false)
    @NonNull
    private Long timestamp;

    @Getter
    @Column(nullable = false, updatable = false)
    @NonNull
    private Integer plots;

    @Getter
    @Type(type = "uuid-char")
    @Column(nullable = false, updatable = false)
    @NonNull
    private UUID customerID;

    @Getter
    @Column(nullable = false)
    @NonNull
    private ItemsBySell itemsBySell;

    @Getter
    @Column(nullable = false)
    @NonNull
    private SellStatus sellStatus;

    @Getter
    @Column(nullable = false, updatable = false)
    @NonNull
    private SellType sellType;

    @Getter
    @Column(nullable = false, updatable = false)
    @NonNull
    private Reservation reservation;
}
