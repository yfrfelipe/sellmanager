package br.com.sellmanager.service;

import br.com.sellmanager.dto.company.CompanyProductDTO;
import br.com.sellmanager.exception.southbound.Product.ProductCreateException;
import br.com.sellmanager.exception.southbound.Product.ProductDeleteException;
import br.com.sellmanager.exception.southbound.Product.ProductNotFoundException;
import java.util.UUID;

public interface ProductService extends AbstractService<
        CompanyProductDTO,
        CompanyProductDTO,
        UUID,
        ProductCreateException,
        ProductNotFoundException,
        ProductDeleteException> {
}
