package br.com.sellmanager.model.request;


import br.com.sellmanager.model.AbstractEntity;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity(name = "tb_product_model")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductModel extends AbstractEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    @NonNull
    private UUID id;

    @Column(nullable = false)
    @Getter
    @NonNull
    private String model;

    @Getter
    @OneToOne(fetch = FetchType.EAGER)
    @NonNull
    private ProductBrand productBrand;
}
