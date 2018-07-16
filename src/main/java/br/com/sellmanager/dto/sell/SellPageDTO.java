package br.com.sellmanager.dto.sell;

import br.com.sellmanager.dto.AbstractPageDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@JsonIgnoreProperties
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellPageDTO extends AbstractPageDTO {

    @Getter
    @NonNull
    private Long totalElements;
    @Getter
    @NonNull
    private Integer totalPages;
    @Getter
    @NonNull
    private List<SellDTO> content;
    @Getter
    @NonNull
    private Integer currentPage;
}
