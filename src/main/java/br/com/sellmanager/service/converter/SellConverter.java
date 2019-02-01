package br.com.sellmanager.service.converter;

import br.com.sellmanager.dto.sell.ItemDTO;
import br.com.sellmanager.dto.sell.SellDTO;
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
public class SellConverter implements ConverterService<Sell, SellDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemConverter itemConverter;

    @PostConstruct
    public void init() {
        modelMapper.createTypeMap(SellDTO.class, Sell.class);
        modelMapper.createTypeMap(Sell.class, SellDTO.class);
    }

    @Override
    public Sell fromDto(final SellDTO dto) {
        final Set<Item> items = Sets.newHashSet();
        final Sell sell = modelMapper.map(dto, Sell.class);
        sell.setSellStatus(SellStatus.valueOf(dto.getSellStatusDTO().name()));
        sell.setSellType(SellType.valueOf(dto.getSellTypeDTO().name()));

        for (ItemDTO itemDTO : dto.getItemsDTO()) {
            items.add(itemConverter.fromDto(itemDTO));
        }

        sell.setItems(items);
        return sell;
    }

    @Override
    public SellDTO toDto(final Sell entity) {
        final Set<ItemDTO> itemsDTO = Sets.newHashSet();
        final SellDTO sellDTO = modelMapper.map(entity, SellDTO.class);
        sellDTO.setSellStatusDTO(SellStatusDTO.valueOf(entity.getSellStatus().name()));
        sellDTO.setSellTypeDTO(SellTypeDTO.valueOf(entity.getSellType().name()));

        for (Item item : entity.getItems()) {
            itemsDTO.add(itemConverter.toDto(item));
        }

        sellDTO.setItemsDTO(itemsDTO);
        return sellDTO;
    }
}
