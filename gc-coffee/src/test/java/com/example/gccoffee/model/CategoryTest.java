package com.example.gccoffee.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

  @Test
  void menuTest() {
    Category coffee = Category.COFFEE;
    Category category = Category.findCategory(coffee);

    assertEquals(category.name(), "커피디저트");
    System.out.println(category.name());
  }
  @Test
  void menuFailureTest(){

  }

}