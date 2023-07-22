package com.example.gccoffee.dto.orderItem;

import java.util.UUID;

public record OrderItemCreateRequest(
        UUID productId,
        int quantity
) {
}
