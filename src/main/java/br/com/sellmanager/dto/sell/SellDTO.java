package br.com.sellmanager.dto.sell;

import br.com.sellmanager.dto.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@JsonIgnoreProperties
@Data
@NoArgsConstructor
public class SellDTO extends AbstractDTO {

    @Getter
    @Setter
    @NonNull
    private Integer plots;
    @Getter
    @Setter
    @NonNull
    private UUID customerID;
    @Getter
    @Setter
    @NonNull
    private SellTypeDTO sellTypeDTO;
    @Getter
    @Setter
    @NonNull
    private Set<ItemDTO> itemsDTO;
}
