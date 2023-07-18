package com.example.gccoffee.model;

import com.example.gccoffee.dto.product.ProductMapper;
import com.example.gccoffee.dto.product.ProductResponseDto;
import com.example.gccoffee.dto.product.ProductUpdateDto;
import com.example.gccoffee.repository.ProductJdbcRepository;
import com.example.gccoffee.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class ProductJdbcRepositoryTest {

  private final ProductJdbcRepository productJdbcRepository;
  private final ProductMapper productMapper;
  private final ProductService productService;

  public ProductJdbcRepositoryTest(ProductJdbcRepository productJdbcRepository, ProductMapper productMapper, ProductService productService) {
    this.productJdbcRepository = productJdbcRepository;
    this.productMapper = productMapper;
    this.productService = productService;
  }

  private static final Product newProduct = new Product(UUID.randomUUID(), "아메리카노", Category.COFFEE, 5000, "아메리카농농농");
  private static final int INSERT_TEST_RESULT_SIZE = 1;

  @Test
  @DisplayName("상품을 추가할 수 있다.")
  void insertProduct_Success() {
    productJdbcRepository.insert(newProduct);
    var all = productJdbcRepository.findAll();
    Assertions.assertEquals(all.size(), INSERT_TEST_RESULT_SIZE);
  }

  @Test
  @DisplayName("상품을 이름으로 조회할 수 있다.")
  void findByName_Success() {
    var product = productJdbcRepository.findByName(newProduct.getName());
    Assertions.assertFalse(product.isEmpty());
  }

  @Test
  @DisplayName("상품을 아이디로 조회할 수 있다.")
  void findById_Success() {
    var product = productJdbcRepository.findById(newProduct.getProductId());
    Assertions.assertFalse(product.isEmpty());
  }

  @Test
  @DisplayName("상품을 카테고리로 조회할 수 있다.")
  void findByCategory_Success() {
    var product = productJdbcRepository.findByCategory(Category.COFFEE_BEAN);
    Assertions.assertFalse(product.isEmpty());
  }

  @Test
  @DisplayName("상품을 수정할 수 있다.")
  void updateProduct_Success() {
    ProductUpdateDto updateDto = new ProductUpdateDto(newProduct.getProductId(), "커피콩빵", Category.COFFEE_DESSERT, 3500, "빵빵빵");
    ProductResponseDto responseDto = productService.update(updateDto);
    Assertions.assertEquals(updateDto, responseDto);
  }

  @Test
  @DisplayName("상품을 전체 삭제한다.")
  void testDeleteAll() {
    productJdbcRepository.deleteAll();
    var all = productJdbcRepository.findAll();
    Assertions.assertFalse(all.isEmpty());
  }

}
