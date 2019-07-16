package br.com.sellmanager.service;

import br.com.sellmanager.dto.offer.OfferDTO;
import java.util.Set;
import java.util.UUID;

public interface UserRequestService {

    void handleRequest(UUID userRequest, UUID productModelID);

    void makeOffer(UUID userRequest, UUID companyID, Float value);

    void acceptOffer(UUID companyID, UUID userRequestID);

    Set<OfferDTO> listOpenOffersByCompany(UUID companyID);
}
