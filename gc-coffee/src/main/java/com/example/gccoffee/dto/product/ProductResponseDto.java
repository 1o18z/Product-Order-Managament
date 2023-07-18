package com.example.gccoffee.dto.product;

import com.example.gccoffee.model.Category;

import java.util.UUID;

public record ProductResponseDto(UUID productId, String productName, Category category, long price,
                                 String description) {
}
