package br.com.sellmanager.model.sell;

import br.com.sellmanager.model.AbstractEntity;
import java.util.Date;
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

//    private Date date;
//
//    private Integer plots;
}
