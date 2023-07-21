package com.example.gccoffee.validator;

import com.example.gccoffee.model.Product;
import com.example.gccoffee.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Component
public class ProductValidator {

  private static final int MINUMUM_PRICE = 2000;
  private static final int MAXIMUM_PRICE = 200000;

  private static final String INVALID_PRICE = "2000 ~ 200000 범위 내의 값을 입력하세요.";
  private static final String INVALID_ID = "유효하지 않은 ID - ID가 비어있습니다.";
  private static final String INVALID_NAME = "이미 존재하는 이름입니다.";
  private static final String INVALID_PRODUCT = "존재하지 않는 상품입니다.";

  private final ProductRepository productRepository;

  public ProductValidator(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public void validName(String productName) {
    if (productName == null || productRepository.findByName(productName).isPresent()) {
      throw new IllegalArgumentException(INVALID_NAME);
    }
  }

  public void validPrice(int price) {
    if (price < MINUMUM_PRICE && price > MAXIMUM_PRICE) {
      throw new RuntimeException(INVALID_PRICE);
    }
  }

  public void validProduct(Optional<Product> product) {
    if (product.isEmpty()) {
      throw new NoSuchElementException(INVALID_PRODUCT);
    }
  }

  public static void validId(UUID productId) {
    if (productId == null) {
      throw new IllegalArgumentException(INVALID_ID);
    }
  }

}
