package br.com.sellmanager.control.sell;

import br.com.sellmanager.dto.sell.SellDTO;
import br.com.sellmanager.dto.sell.SellInProgressDTO;
import br.com.sellmanager.dto.sell.SellPageDTO;
import br.com.sellmanager.external.product.ExternalResponse;
import br.com.sellmanager.service.SellResultAction;
import br.com.sellmanager.service.SellService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//WIP
@RestController
@RequestMapping("/sell")
@Api(value = "sellmanager", description = "Sell Operations")
public class SellControl implements SellWebService, Consumer<String> {
    final Logger LOG = LogManager.getLogger(SellControl.class);

    @Autowired
    private SellService sellServiceImpl;

    @Override
    @PostMapping(path = "/")
    @ApiOperation(value = "Initiate a sell process")
    //TODO: Change sell to have multiple products IDs
    public void post(@RequestBody final SellDTO modelDTO) {
        sellServiceImpl.create(modelDTO);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retrieve a given sell from a given ID.")
    @Override
    @ResponseBody
    public SellInProgressDTO get(@PathVariable final Integer id) {
        return sellServiceImpl.retrieve(id);
    }

    //Must be empty, once a sell start it should not be changed from REST API
    @Override
    public void put(Integer id, SellDTO modelDTO) {

    }

    @PutMapping(path = "/finalize/{sellId}")
    @ApiOperation(value = "Finalize a given sell.")
    public void finalizeSell(@PathVariable final Integer sellId) {
        sellServiceImpl.finalizeSell(sellId, this);
    }

    @PutMapping(path = "/cancel/{sellId}")
    @ApiOperation(value = "Cancel a given Sell.")
    public void cancelSell(@PathVariable final Integer sellId) {
        sellServiceImpl.cancelSell(sellId, this);
    }


    @DeleteMapping(path = "/{sellId}")
    @Override
    public void delete(@PathVariable final Integer id) {
        sellServiceImpl.delete(id);
    }

    @GetMapping(path = "/list")
    @ApiOperation(value = "List sells by quantity.")
    @Override
    public SellPageDTO listByQuantity(@RequestParam final Integer quantity, @RequestParam final Integer pageNum) {
        return sellServiceImpl.listByQuantity(quantity, pageNum);
    }

    @GetMapping(path = "/list/date")
    @ApiOperation(value = "List sells by timestamp.")
    @Override
    public SellPageDTO listBy(@RequestParam final String timestamp,
                              @RequestParam final Integer quantity,
                              @RequestParam final Integer pageNum) {
        return sellServiceImpl.listBy(timestamp, quantity, pageNum);
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public void accept(final String accept) {
        final Gson gson = new Gson();
        final ExternalResponse externalResponse = gson.fromJson(accept, ExternalResponse.class);
        final UUID reservationID = UUID.fromString(externalResponse.getMessage());
        switch (externalResponse.getStatus()) {
            case 200:
                final SellResultAction resultAction = sellServiceImpl.getResultAction(reservationID);
                if (Objects.nonNull(resultAction)) {
                    resultAction.onSuccess(reservationID);
                }
                break;
            case 500:
                LOG.info("Error: {}", accept);
                sellServiceImpl.getResultAction(UUID.fromString(externalResponse.getMessage()))
                        .onFail(accept, reservationID);
        }
    }
}
