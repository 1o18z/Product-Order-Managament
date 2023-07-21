package com.example.gccoffee.service;

import com.example.gccoffee.dto.order.OrderCreateRequest;
import com.example.gccoffee.dto.order.OrderMapper;
import com.example.gccoffee.dto.order.OrderResponse;
import com.example.gccoffee.dto.orderItem.OrderItemCreateRequest;
import com.example.gccoffee.dto.orderItem.OrderItemMapper;
import com.example.gccoffee.model.Order;
import com.example.gccoffee.model.OrderItem;
import com.example.gccoffee.repository.OrderItemRepository;
import com.example.gccoffee.repository.OrderRepository;
import com.example.gccoffee.validator.OrderValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional(readOnly = true)
@Service
public class OrderService {

  private final OrderMapper orderMapper;
  private final OrderRepository orderRepository;
  private final OrderValidator orderValidator;
  private final OrderItemMapper orderItemMapper;
  private final OrderItemRepository orderItemRepository;

  public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, OrderValidator orderValidator, OrderItemMapper orderItemMapper, OrderItemRepository orderItemRepository) {
    this.orderMapper = orderMapper;
    this.orderRepository = orderRepository;
    this.orderValidator = orderValidator;
    this.orderItemMapper = orderItemMapper;
    this.orderItemRepository = orderItemRepository;
  }

  @Transactional
  public OrderResponse create(OrderCreateRequest orderCreateDto) {
    orderValidator.validEmail(orderCreateDto.email());

    Order createdOrder = orderMapper.toOrder(orderCreateDto);
    Order savedOrder = orderRepository.save(createdOrder);

    List<OrderItemCreateRequest> orderItems = orderCreateDto.orderItems();
    List<OrderItem> orderItemList = new ArrayList<>();

    for (OrderItemCreateRequest item : orderItems) {
      OrderItem orderItem = orderItemMapper.toOrderItem(item, savedOrder.getOrderId());
      orderItemList.add(orderItem);
    }

    orderItemRepository.save(orderItemList);
    return orderMapper.toResponse(savedOrder);
  }

  public List<OrderResponse> findAll() {
    return orderRepository.findAll()
            .stream()
            .map(orderMapper::toResponse)
            .toList();
  }

  public OrderResponse findById(UUID orderId) {
    Optional<Order> order = orderRepository.findById(orderId);
    orderValidator.validOrder(order);
    OrderResponse orderResponseDto = orderMapper.toResponse(order.get());
    return orderResponseDto;
  }

  @Transactional
  public void cancel(UUID orderId) {
    Optional<Order> order = orderRepository.findById(orderId);
    orderValidator.validOrder(order);

    orderItemRepository.deleteOrderItems(orderId);
    orderRepository.cancel(orderId);
  }

  @Transactional
  public void deleteAll() {
    orderRepository.deleteAll();
    orderItemRepository.deleteAllOrderItems();
  }

}
