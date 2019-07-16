package br.com.sellmanager.model.sell;

import br.com.sellmanager.model.AbstractEntity;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity(name = "tb_items_by_sell")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ItemsBySell extends AbstractEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "items_by_sell_gen", sequenceName = "items_by_sell_seq")
    @Column(nullable = false, updatable = false)
    @NonNull
    private Integer id;

    @Column(nullable = false)
    @Getter
    @NonNull
    private Integer sellId;

    @Column(nullable = false)
    @Getter
    @NonNull
    @ElementCollection
    private Set<Integer> itemId;
}
