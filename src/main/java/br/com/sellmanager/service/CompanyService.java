package br.com.sellmanager.service;

import br.com.sellmanager.dto.company.CompanyDTO;
import br.com.sellmanager.dto.company.CompanyReqDTO;
import br.com.sellmanager.exception.southbound.company.CompanyCreateException;
import br.com.sellmanager.exception.southbound.company.CompanyDeleteException;
import br.com.sellmanager.exception.southbound.company.CompanyNotFoundException;
import java.util.Set;
import java.util.UUID;

public interface CompanyService extends AbstractService<
        CompanyDTO,
        CompanyReqDTO,
        UUID,
        CompanyCreateException,
        CompanyNotFoundException,
        CompanyDeleteException> {


    Set<UUID> listCompaniesWithModelID(UUID productID);
}
