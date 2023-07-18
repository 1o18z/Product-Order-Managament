package com.example.gccoffee.validator;

import com.example.gccoffee.model.Order;

import java.util.NoSuchElementException;
import java.util.Optional;

public class OrderValidator {

  public static void checkExist(Optional<Order> order){
    if(order.isEmpty()){
      throw new NoSuchElementException("No Exist");
    }
  }

}
