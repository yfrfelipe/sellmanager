package br.com.sellmanager.control.offer;

import br.com.sellmanager.dto.offer.OfferDTO;
import br.com.sellmanager.service.UserRequestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offer")
@Api(value = "offer", description = "Offer operations.")
public class OfferControl implements OfferWebService {
    final Logger LOG = LogManager.getLogger(OfferControl.class);

    @Autowired
    private UserRequestService userRequestService;

    @PostMapping(path = "/makeoffer")
    @ApiOperation(value = "Make an offer to a given request")
    public void makeOffer(@RequestBody final OfferDTO offerDTO) {
        //TODO: Validate fields
        userRequestService.makeOffer(offerDTO.getCompanyID(), offerDTO.getRequestID(), offerDTO.getValue());
    }

    @Override
    public void post(OfferDTO modelDTO) {

    }

    @Override
    public OfferDTO get(Integer id) {
        return null;
    }

    @Override
    public void put(Integer id, OfferDTO modelDTO) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void close() throws Exception {

    }
}
