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

@Entity(name = "tb_company")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Company extends AbstractEntity {

    @Id
    @Getter
    @Column(nullable = false, updatable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @NonNull
    private UUID id;

    @Column(nullable = false)
    @Getter
    @NonNull
    private String name;

    @Column(nullable = false)
    @Getter
    @NonNull
    private String registry;

    @Column(nullable = false)
    @Getter
    @NonNull
    private Double latitude;

    @Column(nullable = false)
    @Getter
    @NonNull
    private Double longitude;
}
