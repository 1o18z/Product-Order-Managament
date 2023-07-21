package com.example.gccoffee.repository;

import com.example.gccoffee.dto.product.ProductUpdateDto;
import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class ProductJdbcRepositoryTest {

  @Autowired
  ProductJdbcRepository productJdbcRepository;

  private final Product firstProduct = new Product(UUID.randomUUID(), "Test1", Category.COFFEE, 5000);
  private final Product secondProduct = new Product(UUID.randomUUID(), "Test2", Category.COFFEE_DESSERT, 7000);
  private final Product thridProduct = new Product(UUID.randomUUID(), "Test3", Category.COFFEE, 5500);

  private static final int FIND_TEST_RESULT_SIZE = 2;
  private static final int DELETE_TEST_RESULT_SIZE = 0;



  @Test
  @DisplayName("상품을 추가할 수 있다.")
  void insertProduct_Success() {
    productJdbcRepository.insert(firstProduct);
    productJdbcRepository.insert(secondProduct);

    List<Product> products = productJdbcRepository.findAll();

    Assertions.assertEquals(products.size(), FIND_TEST_RESULT_SIZE);
  }

  @Test
  @DisplayName("상품을 이름으로 조회할 수 있다.")
  void findByName_Success() {
    productJdbcRepository.insert(secondProduct);

    Optional<Product> findProduct = productJdbcRepository.findByName(secondProduct.getProductName());
    Product product = findProduct.get();

    Assertions.assertEquals(product.getProductId(), secondProduct.getProductId());
  }

  @Test
  @DisplayName("상품을 아이디로 조회할 수 있다.")
  void findById_Success() {
    productJdbcRepository.insert(firstProduct);
    productJdbcRepository.insert(secondProduct);

    Optional<Product> findProduct = productJdbcRepository.findById(firstProduct.getProductId());
    Product product = findProduct.get();

    Assertions.assertEquals(product.getProductName(), firstProduct.getProductName());
  }

  @Test
  @DisplayName("상품을 카테고리로 조회할 수 있다.")
  void findByCategory_Success() {
    Category category = Category.COFFEE;
    productJdbcRepository.insert(firstProduct);
    productJdbcRepository.insert(secondProduct);
    productJdbcRepository.insert(thridProduct);

    List<Product> findCategory = productJdbcRepository.findByCategory(category);

    Assertions.assertEquals(findCategory.size(), FIND_TEST_RESULT_SIZE);
    Assertions.assertEquals(category, findCategory.get(0).getCategory());
    Assertions.assertEquals(category, findCategory.get(1).getCategory());
  }

  @Test
  @DisplayName("상품을 수정할 수 있다.")
  void updateProduct_Success() {
    productJdbcRepository.insert(firstProduct);

    ProductUpdateDto updateDto = new ProductUpdateDto(firstProduct.getProductId(), "Test4", Category.COFFEE_DESSERT, 3500);
    Product product = productJdbcRepository.update(updateDto);

    Assertions.assertNotEquals(firstProduct.getProductName(), product.getProductName());
    Assertions.assertNotEquals(firstProduct.getCategory(), product.getCategory());
  }

  @Test
  @DisplayName("상품을 전체 삭제할 수 있다.")
  void deleteAll_Success() {
    productJdbcRepository.insert(firstProduct);
    productJdbcRepository.insert(secondProduct);
    int beforeSize = productJdbcRepository.findAll().size();
    Assertions.assertEquals(beforeSize, FIND_TEST_RESULT_SIZE);

    productJdbcRepository.deleteAll();
    int afterSize = productJdbcRepository.findAll().size();
    Assertions.assertEquals(afterSize, DELETE_TEST_RESULT_SIZE);

    Assertions.assertNotEquals(beforeSize, afterSize);
  }

  @Test
  @DisplayName("상품을 아이디로 삭제할 수 있다.")
  void deleteById_Success() {
    productJdbcRepository.insert(firstProduct);
    productJdbcRepository.insert(secondProduct);

    productJdbcRepository.deleteById(firstProduct.getProductId());

    productJdbcRepository.findById(firstProduct.getProductId());
  }

}
