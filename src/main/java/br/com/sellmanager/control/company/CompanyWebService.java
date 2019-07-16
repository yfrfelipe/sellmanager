package br.com.sellmanager.control.company;

import br.com.sellmanager.control.AbstractWebService;
import br.com.sellmanager.dto.company.CompanyDTO;
import br.com.sellmanager.dto.company.CompanyReqDTO;
import java.util.UUID;

public interface CompanyWebService extends AbstractWebService<CompanyDTO, CompanyReqDTO, UUID> {
}
