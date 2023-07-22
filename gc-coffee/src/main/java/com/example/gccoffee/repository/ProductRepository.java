package com.example.gccoffee.repository;

import com.example.gccoffee.dto.product.ProductUpdateRequest;
import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

  Product save(Product product);

  Product update(ProductUpdateRequest productUpdateDto);

  Optional<Product> findById(UUID productId);

  Optional<Product> findByName(String productName);

  List<Product> findByCategory(Category category);

  List<Product> findAll();

  void deleteAll();

  void deleteById(UUID productId);

}
