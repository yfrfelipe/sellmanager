package br.com.sellmanager.dto.company;

import br.com.sellmanager.dto.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@JsonIgnoreProperties
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO extends AbstractDTO {

    @Getter
    @NonNull
    private String name;
    @Getter
    @NonNull
    private String registry;
    @Getter
    @NonNull
    private Double latitude;
    @Getter
    @NonNull
    private Double longitude;
}
