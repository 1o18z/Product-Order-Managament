package com.example.gccoffee.controller.api;

import com.example.gccoffee.dto.order.OrderCreateDto;
import com.example.gccoffee.dto.order.OrderResponseDto;
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
  public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderCreateDto orderCreateDto) {
    OrderResponseDto responseDto = orderService.create(orderCreateDto);
    return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<OrderResponseDto>> findAll() {
    List<OrderResponseDto> responseDtoList = orderService.findAll();
    return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<OrderResponseDto> findById(@PathVariable UUID orderId) {
    OrderResponseDto responseDto = orderService.findById(orderId);
    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @DeleteMapping("/{orderId}")
  public ResponseEntity<Void> cancel(@PathVariable UUID orderId) {
    orderService.cancel(orderId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteAll() {
    orderService.deleteAll();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
