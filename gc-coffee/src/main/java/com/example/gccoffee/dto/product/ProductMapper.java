package com.example.gccoffee.dto.product;

import com.example.gccoffee.model.Product;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductMapper {

  public ProductResponseDto mapToResponse(Product product) {
    return new ProductResponseDto(
            product.getProductId(),
            product.getProductName(),
            product.getCategory(),
            product.getPrice(),
            product.getDescription()
    );
  }

  public Product toProduct(ProductCreateDto productCreateDto) {
    return new Product(
            UUID.randomUUID(),
            productCreateDto.productName(),
            productCreateDto.category(),
            productCreateDto.price(),
            productCreateDto.description()
    );
  }

}
