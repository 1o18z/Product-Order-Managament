package com.example.gccoffee.dto.order;

import com.example.gccoffee.dto.orderItem.OrderItemCreateDto;

import java.util.List;

public record OrderCreateDto(String email, String address, List<OrderItemCreateDto> orderItems) {
}
