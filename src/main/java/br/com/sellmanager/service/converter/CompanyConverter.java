package br.com.sellmanager.service.converter;

import br.com.sellmanager.dto.company.CompanyDTO;
import br.com.sellmanager.model.company.Company;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyConverter implements ConverterService<Company, CompanyDTO> {

    @Autowired
    private ModelMapper modelMapper;

    public void init() {
        modelMapper.createTypeMap(CompanyDTO.class, Company.class);
        modelMapper.createTypeMap(Company.class, CompanyDTO.class);
    }

    @Override
    public Company fromDto(final CompanyDTO dto) {
        final Company company = modelMapper.map(dto, Company.class);
        return company;
    }

    @Override
    public CompanyDTO toDto(final Company entity) {
        final CompanyDTO companyDTO = modelMapper.map(entity, CompanyDTO.class);
        return companyDTO;
    }
}
