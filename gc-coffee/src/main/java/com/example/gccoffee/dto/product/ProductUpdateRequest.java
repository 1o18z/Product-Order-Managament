package com.example.gccoffee.dto.product;

import com.example.gccoffee.model.Category;

import java.util.UUID;

public record ProductUpdateRequest(
        UUID productId,
        String productName,
        Category category,
        int price
) {
}
