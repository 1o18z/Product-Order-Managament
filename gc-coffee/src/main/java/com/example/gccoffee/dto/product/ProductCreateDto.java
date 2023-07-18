package com.example.gccoffee.dto.product;

import com.example.gccoffee.model.Category;

public record ProductCreateDto(String productName, Category category, long price, String description) {
}
