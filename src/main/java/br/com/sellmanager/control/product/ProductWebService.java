package br.com.sellmanager.control.product;

import br.com.sellmanager.control.AbstractWebService;
import br.com.sellmanager.dto.Product.ProductBrandDTO;
import br.com.sellmanager.dto.Product.ProductTypeDTO;
import br.com.sellmanager.dto.company.CompanyProductDTO;
import java.util.List;
import java.util.UUID;

public interface ProductWebService extends AbstractWebService<CompanyProductDTO, CompanyProductDTO, UUID> {

    List<ProductTypeDTO> productTypes(String productType);

    List<ProductBrandDTO> productBrands(UUID productTypeID);

    List<UUID> productModelId(UUID productTypeID, UUID productBrandID);
}
