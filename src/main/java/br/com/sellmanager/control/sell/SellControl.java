package br.com.sellmanager.control.sell;

import br.com.sellmanager.dto.sell.SellDTO;
import br.com.sellmanager.service.SellService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//WIP
@RestController
@RequestMapping("/sell")
@Api(value = "sellmanager", description = "Sell Operations")
public class SellControl implements SellWebService {

    @Autowired
    private SellService sellServiceImpl;

    @Override
    public void post(final SellDTO modelDTO) {
        sellServiceImpl.create(modelDTO);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retrieve a given sell from a given ID.")
    @Override
    @ResponseBody
    public SellDTO get(@PathVariable final Integer id) {
        return sellServiceImpl.retrieve(id);
    }

    @PutMapping(path = "/finalize/{sellId}")
    @ApiOperation(value = "Finalize a given sell.")
    public void finalizeSell(@PathVariable final Integer sellId) {
        sellServiceImpl.finalizeSell(sellId);
    }

    @PutMapping(path = "/cancel/{sellId}")
    @ApiOperation(value = "Cancel a given Sell.")
    public void cancelSell(@PathVariable final Integer sellId) {
        sellServiceImpl.cancelSell(sellId);
    }

    @PutMapping(path = "/{sellId}")
    @Override
    public void put(@PathVariable final Integer id,
                    @RequestBody final SellDTO modelDTO) {
        sellServiceImpl.update(id, modelDTO);
    }

    @DeleteMapping(path = "/{sellId}")
    @Override
    public void delete(@PathVariable final Integer id) {
        sellServiceImpl.delete(id);
    }

    @Override
    public void close() throws Exception {

    }
}
