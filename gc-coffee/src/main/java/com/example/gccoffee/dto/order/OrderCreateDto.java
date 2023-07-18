package com.example.gccoffee.dto.order;

import com.example.gccoffee.model.Product;

public record OrderCreateDto (String email, String address, Product product, int quantity) {
}
