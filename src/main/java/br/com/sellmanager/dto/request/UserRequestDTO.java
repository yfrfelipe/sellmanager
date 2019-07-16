package br.com.sellmanager.dto.request;

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
public class UserRequestDTO extends AbstractDTO{

    @Getter
    @Setter
    @NonNull
    private UUID userID;
    @Getter
    @Setter
    @NonNull
    private UUID productModelID;
    @Getter
    @Setter
    @NonNull
    private Long latitude;
    @Getter
    @Setter
    @NonNull
    private Long longitude;
}
