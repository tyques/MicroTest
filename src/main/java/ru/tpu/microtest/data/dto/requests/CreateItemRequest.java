package ru.tpu.microtest.data.dto.requests;

public record CreateItemRequest(
        String name,
        String info,
        double price
) {
}
