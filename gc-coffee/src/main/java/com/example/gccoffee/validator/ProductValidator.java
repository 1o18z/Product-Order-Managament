package com.example.gccoffee.validator;

import com.example.gccoffee.model.Product;

import java.util.NoSuchElementException;
import java.util.Optional;

public class ProductValidator {

  public static void checkExist(Optional<Product> product){
    if(product.isEmpty()){
      throw new NoSuchElementException("No Exist");
    }
  }

}
