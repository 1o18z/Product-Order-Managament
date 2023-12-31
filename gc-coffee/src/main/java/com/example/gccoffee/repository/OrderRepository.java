package com.example.gccoffee.repository;

import com.example.gccoffee.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {

  Order save(Order order);

  List<Order> findAll();

  Optional<Order> findById(UUID orderId);

  void cancel(UUID orderId);

  void deleteAll();

}
