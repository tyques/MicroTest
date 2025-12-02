package ru.tpu.microtest.mappers.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.tpu.microtest.data.dto.responses.ItemResponse;
import ru.tpu.microtest.data.entities.ItemEntity;
import ru.tpu.microtest.mappers.Mapper;

@Component
@RequiredArgsConstructor
public class ItemMapper implements Mapper<ItemEntity, ItemResponse> {
    private final ModelMapper modelMapper;


    @Override
    public ItemResponse mapToDto(ItemEntity itemEntity) {
        return modelMapper.map(itemEntity, ItemResponse.class);
    }

    @Override
    public ItemEntity mapFromDto(ItemResponse itemResponse) {
        return modelMapper.map(itemResponse, ItemEntity.class);
    }
}
