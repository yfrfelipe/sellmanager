package br.com.sellmanager.control.validator;

import br.com.sellmanager.dto.AbstractDTO;
import br.com.sellmanager.exception.southbound.Product.ProductCreateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ProductValidator extends AbstractDTO {

    @ResponseBody
    @ExceptionHandler(ProductCreateException.class)
    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    public String handleProductCreationException(final ProductCreateException e) {
        return e.getMessage();
    }
}
