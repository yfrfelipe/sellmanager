package br.com.sellmanager.control.validator;

import br.com.sellmanager.exception.northbound.SellFinalizeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class SellValidator extends AbstractValidator {
    private static final Logger LOG = LogManager.getLogger(SellValidator.class);

    @ResponseBody
    @ExceptionHandler(SellFinalizeException.class)
    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    public String sellCouldNotFinalizeHandler(final SellFinalizeException e) {
        return e.getMessage();
    }
}
