package com.example.gccoffee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItem {
  private final Long orderItemId;
  private final Long orderId;
  private final Long productId;
  private int quantity;

}
