package com.example.gccoffee.dto.product;

import com.example.gccoffee.model.Category;

public record ProductUpdateDataDto(String productName, Category category, long price, String description) {
}
