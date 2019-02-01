package br.com.sellmanager.service.converter;

import br.com.sellmanager.dto.sell.ItemDTO;
import br.com.sellmanager.model.sell.Item;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemConverter implements ConverterService<Item, ItemDTO> {

    @Autowired
    private ModelMapper modelMapper;

    public void init() {
        modelMapper.createTypeMap(ItemDTO.class, Item.class);
        modelMapper.createTypeMap(Item.class, ItemDTO.class);
    }

    @Override
    public Item fromDto(final ItemDTO dto) {
        final Item item = modelMapper.map(dto, Item.class);
        return item;
    }

    @Override
    public ItemDTO toDto(final Item entity) {
        final ItemDTO itemDTO = modelMapper.map(entity, ItemDTO.class);
        return itemDTO;
    }
}
