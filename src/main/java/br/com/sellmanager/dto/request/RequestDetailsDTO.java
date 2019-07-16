package br.com.sellmanager.dto.request;

import br.com.sellmanager.dto.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@JsonIgnoreProperties
@AllArgsConstructor
public class RequestDetailsDTO extends AbstractDTO {

    @Getter
    @NonNull
    private String productType;
    @Getter
    @NonNull
    private String productBrand;

    @Getter
    private String productModel;

    @Getter
    @NonNull
    private Integer quantity;
}
