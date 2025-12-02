package ru.tpu.microtest.services;

import ru.tpu.microtest.data.dto.requests.CreateItemRequest;
import ru.tpu.microtest.data.dto.responses.ItemResponse;
import ru.tpu.microtest.data.entities.ItemEntity;

import java.util.List;

public interface ItemService {
    List<ItemResponse> findAllItems();

    ItemResponse findById(Long id);

    void deleteById(Long Id);

    ItemResponse create(CreateItemRequest createItemRequest);

    ItemResponse updateById(Long id, CreateItemRequest item);
}
