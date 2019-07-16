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
public class SellInProgressDTO extends AbstractDTO {

    @Getter
    @Setter
    private Integer id;
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
    private UUID customerID;
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

    public SellInProgressDTO(final SellInProgressDTO sellInProgressDTO) {
        this.timestamp = sellInProgressDTO.getTimestamp();
        this.plots = sellInProgressDTO.getPlots();
        this.customerID = sellInProgressDTO.getCustomerID();
        this.sellTypeDTO = sellInProgressDTO.getSellTypeDTO();
        this.sellStatusDTO = sellInProgressDTO.getSellStatusDTO();
        this.itemsDTO = sellInProgressDTO.getItemsDTO();
        this.reservationID = sellInProgressDTO.getReservationID();
    }
}
