package ru.tpu.microtest.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tpu.microtest.data.dto.requests.CreateItemRequest;
import ru.tpu.microtest.data.dto.responses.ItemResponse;
import ru.tpu.microtest.data.entities.ItemEntity;
import ru.tpu.microtest.mappers.Mapper;
import ru.tpu.microtest.repositories.ItemRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService{
    private final Mapper<ItemEntity, ItemResponse> mapper;
    private final ItemRepository itemRepository;

    @Override
    public List<ItemResponse> findAllItems() {
        return itemRepository.findAll().stream()
                .map(mapper::mapToDto)
                .toList();
    }

    @Override
    public ItemResponse findById(Long id) {
        return mapper.mapToDto(itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item " + id + " not found")));
    }

    @Override
    public void deleteById(Long Id) {
        itemRepository.deleteById(Id);
    }

    @Override
    public ItemResponse create(CreateItemRequest createItemRequest) {
        ItemEntity itemEntity = ItemEntity.builder()
                .name(createItemRequest.name())
                .info(createItemRequest.info())
                .price(createItemRequest.price())
                .build();


        return mapper.mapToDto(itemRepository.save(itemEntity));
    }

    @Override
    public ItemResponse updateById(Long id, CreateItemRequest item) {
        ItemEntity itemEntity = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item " + id + " not found"));

        itemEntity.setName(item.name());
        itemEntity.setInfo(item.info());
        itemEntity.setPrice(item.price());

        return mapper.mapToDto(itemRepository.save(itemEntity));
    }
}
