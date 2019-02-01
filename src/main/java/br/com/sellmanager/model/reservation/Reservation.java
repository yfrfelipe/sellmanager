package br.com.sellmanager.model.reservation;

import br.com.sellmanager.model.AbstractEntity;
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


@Entity(name = "tb_reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Reservation extends AbstractEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "reservation_gen", sequenceName = "reservation_seq")
    @Column(nullable = false, updatable = false)
    @NonNull
    private Integer id;

    @Column(nullable = false, updatable = false)
    @Getter
    @NonNull
    private UUID transactionID;

    @Column(nullable = false, updatable = false)
    @Getter
    @NonNull
    private Long timestamp;
}
