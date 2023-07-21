package com.example.gccoffee.repository;

import com.example.gccoffee.model.OrderItem;

import java.util.List;
import java.util.UUID;

public interface OrderItemRepository {

  UUID add(List<OrderItem> orderItems);

}
