package com.example.gccoffee.dto.order;

import java.util.UUID;

public record OrderResponse(
        UUID orderId,
        String email,
        String address
) {
}
