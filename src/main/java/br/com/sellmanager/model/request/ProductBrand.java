package br.com.sellmanager.model.request;

import br.com.sellmanager.model.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity(name = "tb_product_brand")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductBrand extends AbstractEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "product_brand_gen", sequenceName = "product_brand_seq")
    @Column(nullable = false, updatable = false)
    @NonNull
    private Integer id;

    @Column(nullable = false)
    @Getter
    @NonNull
    private String brand;

    @Getter
    @OneToOne(fetch = FetchType.EAGER)
    @NonNull
    private ProductType productType;
}
