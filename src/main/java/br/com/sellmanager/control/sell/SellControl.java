package br.com.sellmanager.control.sell;

import br.com.sellmanager.dto.sell.SellDTO;
import br.com.sellmanager.external.product.ProductClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sell")
@Api(value = "sellmanager", description = "Sell Operations")
public class SellControl implements SellWebService {

    @Autowired
    private ProductClientService productClientService;

    @Override
    public void post(SellDTO modelDTO) {

    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retrieve a given sell from a given ID.")
    @Override
    @ResponseBody
    public SellDTO get(@PathVariable final Integer id) {
        productClientService.getProduct();
        return new SellDTO();
    }

    @Override
    public void put(Integer id, SellDTO modelDTO) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void close() throws Exception {

    }
}
