package br.com.sellmanager.service.converter;

import br.com.sellmanager.dto.company.CompanyProductDTO;
import br.com.sellmanager.model.company.CompanyProduct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductConverter implements ConverterService<CompanyProduct, CompanyProductDTO> {

    @Autowired
    private ModelMapper modelMapper;

    public void init() {
        modelMapper.createTypeMap(CompanyProduct.class, CompanyProductDTO.class);
        modelMapper.createTypeMap(CompanyProductDTO.class, CompanyProduct.class);
    }

    @Override
    public CompanyProduct fromDto(final CompanyProductDTO dto) {
        final CompanyProduct companyProduct = modelMapper.map(dto, CompanyProduct.class);
        return companyProduct;
    }

    @Override
    public CompanyProductDTO toDto(final CompanyProduct entity) {
        final CompanyProductDTO companyProductDTO = modelMapper.map(entity, CompanyProductDTO.class);
        return companyProductDTO;
    }
}
