package br.com.sellmanager.dto.company;

import br.com.sellmanager.dto.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@JsonIgnoreProperties
@AllArgsConstructor
public class CompanyReqDTO extends AbstractDTO {

    @Getter
    @NonNull
    private UUID id;
    @Getter
    @NonNull
    private String name;
}