package br.com.sellmanager.dto.sell;

import br.com.sellmanager.dto.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@JsonIgnoreProperties
@Data
@NoArgsConstructor
public class ItemDTO extends AbstractDTO {

    @Getter
    @Setter
    @NonNull
    private Integer productID;
    @Getter
    @Setter
    @NonNull
    private Float price;
    @Getter
    @Setter
    @NonNull
    private Integer quantity;
    @Getter
    @Setter
    @NonNull
    private Integer discount;
}
