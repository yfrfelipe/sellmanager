package br.com.sellmanager.service;

import br.com.sellmanager.dto.company.CompanyProductDTO;
import br.com.sellmanager.exception.southbound.Product.ProductCreateException;
import br.com.sellmanager.exception.southbound.Product.ProductDeleteException;
import br.com.sellmanager.exception.southbound.Product.ProductNotFoundException;
import br.com.sellmanager.model.company.CompanyProduct;
import br.com.sellmanager.persistence.CompanyPersistence;
import br.com.sellmanager.persistence.ProductPersistence;
import br.com.sellmanager.service.converter.ProductConverter;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private ProductPersistence productPersistence;

    @Autowired
    private CompanyPersistence companyPersistence;

    @Override
    public CompanyProductDTO create(final CompanyProductDTO entityDTO) throws ProductCreateException {
//        final Optional<Company> company = companyPersistence.findById(entityDTO.getCompanyID());
//        if (company.isPresent()) {
            final CompanyProduct companyProduct = productConverter.fromDto(entityDTO);
//            companyProduct.setId(UUID.randomUUID());
            final CompanyProduct result = productPersistence.save(companyProduct);
//            return productConverter.toDto(result);
//        }
        final StringBuilder builder = new StringBuilder();
        builder.append("Company with ID: ");
        builder.append(entityDTO.getCompanyID());
        builder.append(" does not exist.");
        return productConverter.toDto(result);
    }

    @Override
    public CompanyProductDTO retrieve(final UUID id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public void delete(final UUID id) throws ProductDeleteException {

    }

    @Override
    public void close() throws Exception {

    }
}
