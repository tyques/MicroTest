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
public class ItemService {
    private final Mapper<ItemEntity, ItemResponse> mapper;
    private final ItemRepository itemRepository;

    public List<ItemResponse> findAllItems() {
        return itemRepository.findAll().stream()
                .map(mapper::mapToDto)
                .toList();
    }

    public ItemResponse findById(Long id) {
        return mapper.mapToDto(itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item " + id + " not found")));
    }

    public void deleteById(Long Id) {
        itemRepository.deleteById(Id);
    }

    public ItemResponse create(CreateItemRequest createItemRequest) {
        ItemEntity itemEntity = ItemEntity.builder()
                .name(createItemRequest.name())
                .info(createItemRequest.info())
                .price(createItemRequest.price())
                .build();


        return mapper.mapToDto(itemRepository.save(itemEntity));
    }

    public ItemResponse updateById(Long id, ItemEntity item) {
        ItemEntity itemEntity = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item " + id + " not found"));

        itemEntity.setName(item.getName());
        itemEntity.setInfo(item.getInfo());
        itemEntity.setPrice(item.getPrice());

        return mapper.mapToDto(itemRepository.save(itemEntity));
    }
}
