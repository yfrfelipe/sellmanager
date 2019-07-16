package br.com.sellmanager.control.company;

import br.com.sellmanager.dto.company.CompanyDTO;
import br.com.sellmanager.dto.company.CompanyReqDTO;
import br.com.sellmanager.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@Api(value = "company", description = "Company operations.")
public class CompanyControl implements CompanyWebService {

    @Autowired
    private CompanyService companyService;

    @PostMapping(path = "/")
    @ApiOperation(value = "Create a given company.")
    @Override
    public void post(@RequestBody final CompanyDTO modelDTO) {
        companyService.create(modelDTO);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retrieve a company by UUID.")
    @Override
    public CompanyReqDTO get(@RequestParam final UUID id) {
        return companyService.retrieve(id);
    }

    @PutMapping(path = "/")
    @ApiOperation(value = "Update the company information.")
    @Override
    public void put(final UUID id, final CompanyDTO modelDTO) {

    }

    @Override
    public void delete(final UUID id) {
        companyService.delete(id);
    }


    @Override
    public void close() throws Exception {

    }
}
