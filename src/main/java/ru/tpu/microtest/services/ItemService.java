package ru.tpu.microtest.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.tpu.microtest.data.dto.requests.CreateItemRequest;
import ru.tpu.microtest.data.dto.responses.ItemResponse;
import ru.tpu.microtest.data.entities.ItemEntity;
import ru.tpu.microtest.mappers.Mapper;
import ru.tpu.microtest.repositories.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService {
    private final Mapper<ItemEntity, ItemResponse> mapper;
    private final ItemRepository itemRepository;

    public List<ItemResponse> findAllItems(){
        return itemRepository.findAll().stream()
                .map(mapper::mapToDto)
                .toList();
    }

    public ItemResponse findById(Long id){
        return mapper.mapToDto(itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item " + id + " not found")));
    }

    public void deleteById(Long Id) { itemRepository.deleteById(Id);    }

    public ItemResponse create(CreateItemRequest createItemRequest){
        ItemEntity itemEntity = ItemEntity.builder()
                .name(createItemRequest.name())
                .info(createItemRequest.info())
                .price(createItemRequest.price())
                .build();


        ItemEntity savedItem = itemRepository.save(itemEntity);
    }

    public ItemResponse updateById(Long ID, ItemEntity itemEntity)
    {

    }
}
