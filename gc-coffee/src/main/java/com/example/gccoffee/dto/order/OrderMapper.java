package com.example.gccoffee.dto.order;

import com.example.gccoffee.model.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderMapper {

  public OrderResponseDto mapToResponse(Order order) {
    return new OrderResponseDto(
            order.getOrderId(),
            order.getEmail(),
            order.getAddress(),
            order.getProduct(),
            order.getQuantity()
    );
  }

  public Order toOrder(OrderCreateDto orderCreateDto) {
    return new Order(
            UUID.randomUUID(),
            orderCreateDto.email(),
            orderCreateDto.address(),
            orderCreateDto.product(),
            orderCreateDto.quantity()
    );
  }

}
