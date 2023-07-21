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
  private final OrderValidator orderValidator;

  public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, OrderValidator orderValidator) {
    this.orderMapper = orderMapper;
    this.orderRepository = orderRepository;
    this.orderValidator = orderValidator;
  }

  @Transactional
  public OrderResponseDto create(OrderCreateDto orderCreateDto) {
    orderValidator.validEmail(orderCreateDto.email());
    orderValidator.validQuantity(orderCreateDto.quantity());

    Order createdOrder = orderMapper.toOrder(orderCreateDto);
    Order savedOrder = orderRepository.insert(createdOrder);
    return orderMapper.toResponse(savedOrder);
  }

  public List<OrderResponseDto> findAll() {
    return orderRepository.findAll()
            .stream()
            .map(orderMapper::toResponse)
            .toList();
  }

  public OrderResponseDto findById(UUID orderId) {
    Optional<Order> order = orderRepository.findById(orderId);
    orderValidator.validOrder(order);
    OrderResponseDto orderResponseDto = orderMapper.toResponse(order.get());
    return orderResponseDto;
  }

  @Transactional
  public void cancel(UUID orderId) {
    Optional<Order> order = orderRepository.findById(orderId);
    orderValidator.validOrder(order);
    orderRepository.cancel(orderId);
  }

  @Transactional
  public void deleteAll() {
    orderRepository.deleteAll();
  }

}
