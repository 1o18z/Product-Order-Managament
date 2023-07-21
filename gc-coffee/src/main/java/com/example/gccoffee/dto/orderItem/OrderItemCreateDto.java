package com.example.gccoffee.dto.orderItem;

import java.util.UUID;

public class OrderItemCreateDto {
  private final UUID productId;
  private final int quantity;

  public OrderItemCreateDto(UUID productId, int quantity) {
    this.productId = productId;
    this.quantity = quantity;
  }

  public UUID getProductId() {
    return productId;
  }

  public int getQuantity() {
    return quantity;
  }

}
