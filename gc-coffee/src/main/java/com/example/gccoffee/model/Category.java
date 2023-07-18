package com.example.gccoffee.model;

import java.util.Arrays;

public enum Category {
  COFFEE_BEAN("커피원두"),
  COFFEE_CUP("커피잔"),
  COFFEE_DESSERT("커피디저트"),
  COFFEE("커피"),
  DEFAULT("없음"),
  ;

  private String type;

  Category(String type) {
    this.type = type;
  }

  public static Category findCategory(Category category) {
    return Arrays.stream(Category.values())
            .filter(value -> value == category)
            .findAny()
            .orElse(DEFAULT);
  }

}
