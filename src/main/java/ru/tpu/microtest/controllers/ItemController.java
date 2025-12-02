package ru.tpu.microtest.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tpu.microtest.data.dto.requests.CreateItemRequest;
import ru.tpu.microtest.data.dto.responses.ItemResponse;
import ru.tpu.microtest.services.ItemService;

import java.util.List;

@RestController
@RequestMapping("api/v1/items")
@AllArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<List<ItemResponse>> findAllItems() {
        return new ResponseEntity<>(itemService.findAllItems(), HttpStatus.OK);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemResponse> findItemById(@PathVariable Long itemId) {
        return new ResponseEntity<>(itemService.findById(itemId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemResponse> createItem(@RequestBody CreateItemRequest createItemRequest) {
        return new ResponseEntity<>(itemService.create(createItemRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<ItemResponse> updateItemById(@PathVariable Long itemId, @RequestBody CreateItemRequest createItemRequest) {
        return new ResponseEntity<ItemResponse>(itemService.updateById(itemId, createItemRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<HttpStatus> deleteItemById(@PathVariable Long itemId){
        itemService.deleteById(itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
