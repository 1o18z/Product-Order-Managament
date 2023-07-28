package com.example.gccoffee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Product {
  private final Long productId;
  private String productName;
  private Category category;
  private int price;

}
