package com.example.gccoffee.controller.api;

import com.example.gccoffee.dto.order.OrderCreateRequest;
import com.example.gccoffee.dto.order.OrderResponse;
import com.example.gccoffee.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {

  private final OrderService orderService;

  public OrderRestController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  public ResponseEntity<OrderResponse> create(@RequestBody OrderCreateRequest orderCreateDto) {
    OrderResponse responseDto = orderService.create(orderCreateDto);
    return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<OrderResponse>> findAll() {
    List<OrderResponse> responseDtoList = orderService.findAll();
    return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<OrderResponse> findById(@PathVariable UUID orderId) {
    OrderResponse responseDto = orderService.findById(orderId);
    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @DeleteMapping("/{orderId}")
  public ResponseEntity<Void> cancel(@PathVariable UUID orderId) {
    orderService.cancel(orderId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteAll() {
    orderService.deleteAll();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
