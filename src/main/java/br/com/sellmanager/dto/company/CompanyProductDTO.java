package br.com.sellmanager.dto.company;

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
public class CompanyProductDTO extends AbstractDTO {

    @Getter
    @Setter
    @NonNull
    private String modelID;

    @Getter
    @Setter
    @NonNull
    private String companyID;

    @Getter
    @Setter
    @NonNull
    private Integer quantity;

    @Getter
    @Setter
    @NonNull
    private Float value;
}
