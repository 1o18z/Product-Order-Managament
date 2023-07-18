package com.example.gccoffee.dto.order;

import com.example.gccoffee.model.Product;

import java.util.UUID;

public record OrderResponseDto(UUID orderId, String email, String address, Product product, int quantity) {
}
