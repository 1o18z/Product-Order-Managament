package com.example.gccoffee.model;

import com.example.gccoffee.validator.OrderValidator;

import java.util.UUID;

public class Order {
  private final UUID orderId;
  private final String email;
  private final String address;
  private final OrderStatus orderStatus;

  public Order(UUID orderId, String email, String address, OrderStatus orderStatus) {
    OrderValidator.validId(orderId);
    this.orderId = orderId;
    this.email = email;
    this.address = address;
    this.orderStatus = orderStatus;
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

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

}
