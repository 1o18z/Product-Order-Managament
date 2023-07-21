package com.example.gccoffee.dto.order;

import com.example.gccoffee.model.Order;
import com.example.gccoffee.model.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderMapper {

  public OrderResponse toResponse(Order order) {
    return new OrderResponse(
            order.getOrderId(),
            order.getEmail(),
            order.getAddress()
    );
  }

  public Order toOrder(OrderCreateRequest orderCreateDto) {
    return new Order(
            UUID.randomUUID(),
            orderCreateDto.email(),
            orderCreateDto.address(),
            OrderStatus.ORDER_SUCCESS
    );
  }

}
