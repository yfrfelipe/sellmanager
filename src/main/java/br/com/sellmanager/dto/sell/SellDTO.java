package br.com.sellmanager.dto.sell;

import br.com.sellmanager.dto.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties
@Data
@NoArgsConstructor
public class SellDTO extends AbstractDTO {
}
