package br.com.sellmanager.service;

import br.com.sellmanager.dto.company.CompanyDTO;
import br.com.sellmanager.dto.company.CompanyReqDTO;
import br.com.sellmanager.exception.southbound.company.CompanyCreateException;
import br.com.sellmanager.exception.southbound.company.CompanyDeleteException;
import br.com.sellmanager.exception.southbound.company.CompanyNotFoundException;
import br.com.sellmanager.model.company.Company;
import br.com.sellmanager.persistence.CompanyPersistence;
import br.com.sellmanager.persistence.ProductPersistence;
import br.com.sellmanager.service.converter.CompanyConverter;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private ProductPersistence productPersistence;

    @Autowired
    private CompanyPersistence companyPersistence;

    @Autowired
    private CompanyConverter companyConverter;

    @Override
    public Set<UUID> listCompaniesWithModelID(final UUID modelID) {
        return productPersistence.queryCompaniesWithProductModel(modelID);
    }

    @Override
    public CompanyDTO create(final CompanyDTO entityDTO) throws CompanyCreateException {
        try {
            final Company company = companyConverter.fromDto(entityDTO);
            final Company result = companyPersistence.save(company);
            return companyConverter.toDto(result);
        } catch (PersistenceException e) {
            throw new CompanyCreateException(e.getMessage());
        }
    }

    @Override
    public CompanyReqDTO retrieve(final UUID id) throws CompanyNotFoundException {
        final Optional<Company> optional = companyPersistence.findById(id);
        CompanyReqDTO companyReqDTO;
        if(optional.isPresent()) {
            final Company company = optional.get();
            companyReqDTO = new CompanyReqDTO(company.getId(), company.getName());
        } else {
            final StringBuilder builder = new StringBuilder();
            builder.append("There's no company with ID: ");
            builder.append(id.toString());
            throw new CompanyNotFoundException(builder.toString());
        }
        return companyReqDTO;
    }

    @Override
    public void delete(final UUID id) throws CompanyDeleteException {
        companyPersistence.deleteById(id);
    }


    @Override
    public void close() throws Exception {

    }
}
