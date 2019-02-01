package br.com.sellmanager.dto.sell;

import br.com.sellmanager.dto.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Set;
import java.util.UUID;
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
    private Long timestamp;
    @Getter
    @Setter
    @NonNull
    private Integer plots;
    @Getter
    @Setter
    @NonNull
    private Integer customerID;
    @Getter
    @Setter
    @NonNull
    private SellTypeDTO sellTypeDTO;
    @Getter
    @Setter
    @NonNull
    private SellStatusDTO sellStatusDTO;
    @Getter
    @Setter
    @NonNull
    private Set<ItemDTO> itemsDTO;
    @Getter
    @Setter
    @NonNull
    private UUID reservationID;

    public SellDTO(final SellDTO sellDTO) {
        this.timestamp = sellDTO.getTimestamp();
        this.plots = sellDTO.getPlots();
        this.customerID = sellDTO.getCustomerID();
        this.sellTypeDTO = sellDTO.getSellTypeDTO();
        this.sellStatusDTO = sellDTO.getSellStatusDTO();
        this.itemsDTO = sellDTO.getItemsDTO();
        this.reservationID = sellDTO.getReservationID();
    }
}
