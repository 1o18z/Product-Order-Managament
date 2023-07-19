package com.example.gccoffee.service;

import com.example.gccoffee.dto.order.OrderCreateDto;
import com.example.gccoffee.dto.order.OrderMapper;
import com.example.gccoffee.dto.order.OrderResponseDto;
import com.example.gccoffee.model.Order;
import com.example.gccoffee.repository.OrderRepository;
import com.example.gccoffee.validator.OrderValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional(readOnly = true)
@Service
public class OrderService {

  private final OrderMapper orderMapper;
  private final OrderRepository orderRepository;

  public OrderService(OrderMapper orderMapper, OrderRepository orderRepository) {
    this.orderMapper = orderMapper;
    this.orderRepository = orderRepository;
  }

  @Transactional
  public OrderResponseDto create(OrderCreateDto orderCreateDto) {
    Order createdOrder = orderMapper.toOrder(orderCreateDto);
    Order savedOrder = orderRepository.insert(createdOrder);
    return orderMapper.mapToResponse(savedOrder);
  }

  public List<OrderResponseDto> findAll() {
    return orderRepository.findAll()
            .stream()
            .map(orderMapper::mapToResponse)
            .toList();
  }

  public OrderResponseDto findById(UUID orderId) {
    Optional<Order> order = orderRepository.findById(orderId);
    OrderValidator.checkExist(order);
    OrderResponseDto orderResponseDto = orderMapper.mapToResponse(order.get());
    return orderResponseDto;
  }

  @Transactional
  public void cancel(UUID orderId) {
    orderRepository.cancel(orderId);
  }

}
