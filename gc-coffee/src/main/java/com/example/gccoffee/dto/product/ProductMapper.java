package com.example.gccoffee.dto.product;

import com.example.gccoffee.model.Product;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductMapper {

  public ProductResponse toResponse(Product product) {
    return new ProductResponse(
            product.getProductId(),
            product.getProductName(),
            product.getCategory(),
            product.getPrice()
    );
  }

  public Product toProduct(ProductCreateRequest productCreateDto) {
    return new Product(
            UUID.randomUUID(),
            productCreateDto.productName(),
            productCreateDto.category(),
            productCreateDto.price()
    );
  }

}
