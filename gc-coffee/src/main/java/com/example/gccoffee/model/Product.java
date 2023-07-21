package com.example.gccoffee.model;

import com.example.gccoffee.validator.ProductValidator;

import java.util.UUID;

public class Product {
  private final UUID productId;
  private String productName;
  private Category category;
  private int price;

  public Product(UUID productId, String name, Category category, int price) {
    ProductValidator.validId(productId);
    this.productId = productId;
    this.productName = name;
    this.category = category;
    this.price = price;
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

}
