package br.com.sellmanager.dto.offer;

import br.com.sellmanager.dto.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.UUID;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@JsonIgnoreProperties
@Data
@NoArgsConstructor
public class OfferDTO extends AbstractDTO {

    @Getter
    @Setter
    @NonNull
    private UUID companyID;
    @Getter
    @Setter
    @NonNull
    private UUID requestID;
    @Getter
    @Setter
    @NonNull
    private Float value;
}
