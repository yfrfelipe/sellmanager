package br.com.sellmanager.service.converter;

import br.com.sellmanager.dto.offer.OfferDTO;
import br.com.sellmanager.model.offer.Offer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferConverter implements ConverterService<Offer, OfferDTO> {

    @Autowired
    private ModelMapper modelMapper;

    public void init() {
        modelMapper.createTypeMap(OfferDTO.class, Offer.class);
        modelMapper.createTypeMap(Offer.class, OfferDTO.class);
    }

    @Override
    public Offer fromDto(final OfferDTO dto) {
        final Offer offer = modelMapper.map(dto, Offer.class);
        return offer;
    }

    @Override
    public OfferDTO toDto(final Offer entity) {
        final OfferDTO offerDTO = modelMapper.map(entity, OfferDTO.class);
        return offerDTO;
    }
}
