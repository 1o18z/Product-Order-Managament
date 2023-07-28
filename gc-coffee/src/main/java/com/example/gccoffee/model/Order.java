package com.example.gccoffee.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderId;

  @Email
  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String address;

  @Column(nullable = false)
  private OrderStatus orderStatus;

}
