package com.example.gccoffee.model;

import java.util.UUID;

public class Product {
  private final UUID productId;
  private String productName;
  private Category category;
  private int price;

  public Product(UUID productId, String productName, Category category, int price) {
    this.productId = productId;
    this.productName = productName;
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
