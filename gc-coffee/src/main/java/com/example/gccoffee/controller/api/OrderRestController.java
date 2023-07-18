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
@RequestMapping("/order")
public class OrderRestController {

  private final OrderService orderService;

  public OrderRestController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping("/create")
  public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderCreateDto orderCreateDto) {
    OrderResponseDto responseDto = orderService.create(orderCreateDto);
    return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
  }

  @GetMapping("/list")
  public ResponseEntity<List<OrderResponseDto>> findAll() {
    List<OrderResponseDto> responseDtoList = orderService.findAll();
    return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<OrderResponseDto> findById(@PathVariable UUID orderId) {
    OrderResponseDto responseDto = orderService.findById(orderId);
    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public void cancel(UUID orderId) {
    orderService.cancel(orderId);
  }

}
