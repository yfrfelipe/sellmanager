package br.com.sellmanager.service.converter;

import br.com.sellmanager.dto.sell.ItemDTO;
import br.com.sellmanager.dto.sell.SellInProgressDTO;
import br.com.sellmanager.dto.sell.SellStatusDTO;
import br.com.sellmanager.dto.sell.SellTypeDTO;
import br.com.sellmanager.model.sell.Item;
import br.com.sellmanager.model.sell.Sell;
import br.com.sellmanager.model.sell.SellStatus;
import br.com.sellmanager.model.sell.SellType;
import com.google.common.collect.Sets;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellInProgressConverter implements ConverterService<Sell, SellInProgressDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemConverter itemConverter;

    @PostConstruct
    public void init() {
        modelMapper.createTypeMap(SellInProgressDTO.class, Sell.class);
        modelMapper.createTypeMap(Sell.class, SellInProgressDTO.class);
    }

    @Override
    public Sell fromDto(final SellInProgressDTO dto) {
        final Sell sell = modelMapper.map(dto, Sell.class);
        sell.setSellStatus(SellStatus.valueOf(dto.getSellStatusDTO().name()));
        sell.setSellType(SellType.valueOf(dto.getSellTypeDTO().name()));

        return sell;
    }

    @Override
    public SellInProgressDTO toDto(final Sell entity) {
        final Set<ItemDTO> itemsDTO = Sets.newHashSet();
        final SellInProgressDTO sellInProgressDTO = modelMapper.map(entity, SellInProgressDTO.class);
        sellInProgressDTO.setSellStatusDTO(SellStatusDTO.valueOf(entity.getSellStatus().name()));
        sellInProgressDTO.setSellTypeDTO(SellTypeDTO.valueOf(entity.getSellType().name()));

        sellInProgressDTO.setItemsDTO(itemsDTO);
        return sellInProgressDTO;
    }
}
