package com.example.gccoffee.dto.orderItem;

import com.example.gccoffee.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderItemMapper {

  public OrderItem toOrderItem(OrderItemCreateDto createDto, UUID orderId) {
    return new OrderItem(
            UUID.randomUUID(),
            orderId,
            createDto.productId(),
            createDto.quantity()
    );
  }

}
