package br.com.sellmanager.model.company;

import br.com.sellmanager.model.AbstractEntity;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity(name = "tb_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CompanyProduct extends AbstractEntity {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Getter
    @NonNull
    private UUID id;

    @Column(nullable = false)
    @Getter
    @NonNull
    private String modelID;

    @Column(nullable = false)
    @Getter
    @NonNull
    private String companyID;

    @Column(nullable = false)
    @Getter
    @NonNull
    private Integer quantity;

    @Column(nullable = false)
    @Getter
    @NonNull
    private Float value;
}
