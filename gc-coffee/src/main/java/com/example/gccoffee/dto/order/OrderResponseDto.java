package com.example.gccoffee.dto.order;

import java.util.UUID;

public record OrderResponseDto(UUID orderId, String email, String address) {
}
