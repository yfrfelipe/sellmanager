package br.com.sellmanager.control.request;

import br.com.sellmanager.dto.offer.OfferDTO;
import br.com.sellmanager.dto.request.RequestDetailsDTO;
import br.com.sellmanager.dto.request.UserRequestDTO;
import br.com.sellmanager.model.offer.Offer;
import br.com.sellmanager.service.UserRequestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Set;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userrequest")
@Api(value = "userrequest", description = "User request operations.")
public class UserRequestControl implements UserRequestWebService {
    final Logger LOG = LogManager.getLogger(UserRequestControl.class);

    @Autowired
    private UserRequestService userRequestService;

    @PostMapping(path = "/")
    @ApiOperation(value = "Create a given user request.")
    @Override
    public void post(@RequestBody final UserRequestDTO userRequestDTO) {
        final UUID userID = userRequestDTO.getUserID();
        final UUID modelID = userRequestDTO.getProductModelID();

        userRequestService.handleRequest(userID, modelID);
    }

    @Override
    public UserRequestDTO get(final UUID id) {
        return null;
    }

    @Override
    public void put(final UUID id, final UserRequestDTO modelDTO) {

    }

    @Override
    public void delete(final UUID id) {

    }

    @PostMapping(path = "/acceptoffer")
    @ApiOperation(value = "Create a given user request.")
    public void acceptOffer(@RequestBody final UUID companyID, @RequestBody final UUID userRequestID) {
        userRequestService.acceptOffer(companyID, userRequestID);
    }

    @GetMapping(path = "/openrequests/{companyID}")
    @ApiOperation(value = "Retrieve a product from a given ID.")
    @ResponseBody
    public Set<OfferDTO> listOpenRequests(@PathVariable final UUID companyID) {
        return userRequestService.listOpenOffersByCompany(companyID);
    }

    @Override
    public void close() throws Exception {

    }
}
