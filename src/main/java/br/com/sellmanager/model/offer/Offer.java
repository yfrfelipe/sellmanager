package br.com.sellmanager.model.offer;

import br.com.sellmanager.model.AbstractEntity;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity(name = "tb_offer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Offer extends AbstractEntity {

    @Id
    @Getter
    @Column(nullable = false, updatable = false, columnDefinition = "BINARY(16)")
    @NonNull
    private UUID id;

    @Column(nullable = false)
    @Getter
    @NonNull
    private UUID requestID;

    @Column(nullable = false)
    @Getter
    @NonNull
    private UUID productModelID;

    @Column(nullable = false)
    @Getter
    @NonNull
    private UUID companyID;

    @Column(nullable = false)
    @Getter
    @NonNull
    private Float offeredValue;

    @Column(nullable = false)
    @Getter
    @NonNull
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;
}
