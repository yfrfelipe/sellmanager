package br.com.sellmanager.control.validator;

import br.com.sellmanager.exception.northbound.IDInvalidException;
import java.util.Objects;

public abstract class AbstractValidator {

    private static final String ID_INVALID_ERROR_MESSAGE = "Invalid ID.";

    public static void validateId(final Integer id) throws IDInvalidException {
        if (Objects.isNull(id)) {
            throw new IDInvalidException(ID_INVALID_ERROR_MESSAGE);
        }
    }
}
