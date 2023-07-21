package com.example.gccoffee.model;

import com.example.gccoffee.validator.OrderValidator;

import java.time.LocalDateTime;
import java.util.UUID;

public class Order {
  private final UUID orderId;
  private final String email;
  private final String address;
  private Product product;
  private final int quantity;
  private OrderStatus orderStatus;
  private final LocalDateTime createdAt;

  public Order(UUID orderId, String email, String address, Product product, OrderStatus orderStatus, int quantity) {
    OrderValidator.validId(orderId);
    this.orderId = orderId;
    this.email = email;
    this.address = address;
    this.product = product;
    this.quantity = quantity;
    this.orderStatus = orderStatus;
    this.createdAt = LocalDateTime.now();
  }

  public UUID getOrderId() {
    return orderId;
  }

  public String getEmail() {
    return email;
  }

  public String getAddress() {
    return address;
  }

  public Product getProduct() {
    return product;
  }

  public int getQuantity() {
    return quantity;
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

}
