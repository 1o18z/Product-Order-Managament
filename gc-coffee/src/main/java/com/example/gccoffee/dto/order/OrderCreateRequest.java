package com.example.gccoffee.dto.order;

import com.example.gccoffee.dto.orderItem.OrderItemCreateRequest;

import java.util.List;

public record OrderCreateRequest(
        String email,
        String address,
        List<OrderItemCreateRequest> orderItems
) {
}
