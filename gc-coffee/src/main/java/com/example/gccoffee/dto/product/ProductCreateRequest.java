package com.example.gccoffee.dto.product;

import com.example.gccoffee.model.Category;

public record ProductCreateRequest(
        String productName,
        Category category,
        int price
) {
}
