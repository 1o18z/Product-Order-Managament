package com.example.gccoffee.repository;

import com.example.gccoffee.model.OrderItem;

import java.util.List;
import java.util.UUID;

public interface OrderItemRepository {

  UUID save(List<OrderItem> orderItems);

  void deleteOrderItems(UUID orderId);

  void deleteAllOrderItems();

}
