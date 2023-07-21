package com.example.gccoffee.model;

import java.util.UUID;
public class OrderItem {
  private final UUID orderItemId;
  private final UUID orderId;
  private final UUID productId;
  private int quantity;

  public UUID getOrderItemId() {
    return orderItemId;
  }

  public UUID getOrderId() {
    return orderId;
  }

  public UUID getProductId() {
    return productId;
  }

  public int getQuantity() {
    return quantity;
  }

  public OrderItem(UUID orderItemId, UUID orderId, UUID productId, int quantity) {
    this.orderItemId = orderItemId;
    this.orderId = orderId;
    this.productId = productId;
    this.quantity = quantity;
  }

}
