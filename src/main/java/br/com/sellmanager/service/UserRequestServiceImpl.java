package br.com.sellmanager.service;

import br.com.sellmanager.dto.offer.OfferDTO;
import br.com.sellmanager.model.offer.Offer;
import br.com.sellmanager.model.offer.RequestStatus;
import br.com.sellmanager.persistence.OfferPersistence;
import br.com.sellmanager.service.converter.ConverterService;
import com.google.common.collect.Sets;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRequestServiceImpl implements UserRequestService {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private OfferPersistence offerPersistence;
    @Autowired
    private ConverterService<Offer, OfferDTO> offerConvertor;

    @Override
    public void handleRequest(final UUID userRequest, final UUID productModelID) {

        final Set<UUID> companyIDs = companyService.listCompaniesWithModelID(productModelID);
        final Set<Offer> offers = buildCompanyOffers(companyIDs, userRequest, productModelID);

        offerPersistence.saveAll(offers);
    }

    @Override
    public void makeOffer(final UUID userRequest, final UUID companyID, final Float value) {
        final Optional<Offer> offerOptional = offerPersistence.findById(userRequest);
        if (offerOptional.isPresent()) {
            final Offer offer = offerOptional.get();
            offer.setRequestStatus(RequestStatus.OFFERED);
            offer.setOfferedValue(value);
            offerPersistence.save(offer);
        }
    }

    //TODO: Should we update all remaining offers as rejected???
    @Override
    public void acceptOffer(final UUID companyID, final UUID userRequestID) {
        final Offer offer = offerPersistence.queryOfferByCompanyAndRequestId(companyID, userRequestID);
        if (Objects.nonNull(offer)) {
            offer.setRequestStatus(RequestStatus.ACCEPTED);
        }
        offerPersistence.save(offer);
    }

    @Override
    public Set<OfferDTO> listOpenOffersByCompany(final UUID companyID) {
        final Set<OfferDTO> offerDTOS = Sets.newHashSet();
        final Set<Offer> offers = offerPersistence.queryOpenOffersByCompany(companyID);
        for (Offer offer : offers) {
            final OfferDTO dto = offerConvertor.toDto(offer);
            offerDTOS.add(dto);
        }
        return offerDTOS;
    }

    private Set<Offer> buildCompanyOffers(final Set<UUID> companyIds, final UUID userRequest, final UUID productModelID) {
        final Set<Offer> offers = Sets.newHashSet();
        companyIds.forEach(companyID -> {
            final Offer companyOffer = new Offer(UUID.randomUUID(), userRequest, productModelID, companyID, Float.valueOf(0), RequestStatus.OPEN);
            offers.add(companyOffer);
        });
        return offers;
    }
}
