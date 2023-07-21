package com.example.gccoffee.model;

import com.example.gccoffee.validator.ProductValidator;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Product {
  private final UUID productId;
  private String productName;
  private Category category;
  private int price;
  private String description;

  public Product(UUID productId, String name, Category category, int price, String description) {
    ProductValidator.validId(productId);
    this.productId = productId;
    this.productName = name;
    this.category = category;
    this.price = price;
    this.description = description;
  }

}
