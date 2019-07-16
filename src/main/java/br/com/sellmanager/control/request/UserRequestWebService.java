package br.com.sellmanager.control.request;

import br.com.sellmanager.control.AbstractWebService;
import br.com.sellmanager.dto.request.RequestDetailsDTO;
import br.com.sellmanager.dto.request.UserRequestDTO;
import java.util.UUID;

public interface UserRequestWebService extends AbstractWebService<UserRequestDTO, UserRequestDTO, UUID> {
}
