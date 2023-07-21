package com.example.gccoffee.model;

import com.example.gccoffee.validator.ProductValidator;

import java.time.LocalDateTime;
import java.util.UUID;

public class Product {
  private final UUID productId;
  private String productName;
  private Category category;
  private int price;
  private String description;
  private final LocalDateTime createdAt;

  public Product(UUID productId, String name, Category category, int price, String description) {
    ProductValidator.validId(productId);
    this.productId = productId;
    this.productName = name;
    this.category = category;
    this.price = price;
    this.description = description;
    this.createdAt = LocalDateTime.now();
  }

  public UUID getProductId() {
    return productId;
  }

  public String getProductName() {
    return productName;
  }

  public Category getCategory() {
    return category;
  }

  public int getPrice() {
    return price;
  }

  public String getDescription() {
    return description;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

}
