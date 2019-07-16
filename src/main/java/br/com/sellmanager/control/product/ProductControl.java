package br.com.sellmanager.control.product;

import br.com.sellmanager.dto.Product.ProductBrandDTO;
import br.com.sellmanager.dto.Product.ProductTypeDTO;
import br.com.sellmanager.dto.company.CompanyProductDTO;
import br.com.sellmanager.service.ProductService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductControl implements ProductWebService {

    @Autowired
    private ProductService productService;

    @PostMapping(path="/")
    @ApiOperation(value = "Create a product.")
    @Override
    public void post(@RequestBody final CompanyProductDTO modelDTO) {
        productService.create(modelDTO);
    }

    @Override
    public CompanyProductDTO get(final UUID id) {
        return null;
    }

    @Override
    public void put(final UUID id, CompanyProductDTO modelDTO) {

    }

    @Override
    public void delete(final UUID id) {

    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public List<ProductTypeDTO> productTypes(final String productType) {
        return null;
    }

    @Override
    public List<ProductBrandDTO> productBrands(UUID productTypeID) {
        return null;
    }

    @Override
    public List<UUID> productModelId(UUID productTypeID, UUID productBrandID) {
        return null;
    }
}
