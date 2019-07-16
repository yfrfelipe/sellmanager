package br.com.sellmanager.external.product;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ExternalResponse implements Serializable {

    @Getter
    @Setter
    private String timestamp;
    @Getter
    @Setter
    private Integer status;
    @Getter
    @Setter
    private String error;
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private String path;
}
