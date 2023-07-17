package com.example.gccoffee.model;

import com.example.gccoffee.repository.ProductJdbcRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class ProductJdbcRepositoryTest {

  @Autowired
  ProductJdbcRepository productJdbcRepository;

  private static final Product newProduct = new Product(UUID.randomUUID(), "new-product", Category.COFFEE_BEAN_PACKAGE, 1000L);

  @Test
  @DisplayName("상품을 추가할 수 있다.")
  void testInsert() {
    productJdbcRepository.insert(newProduct);
    var all = productJdbcRepository.findAll();
    assertThat(all.isEmpty(), is(false));
  }

  @Test
  @DisplayName("상품을 이름으로 조회할 수 있다.")
  void testFindByName() {
    var product = productJdbcRepository.findByName(newProduct.getProductName());
    assertThat(product.isEmpty(), is(false));
  }

  @Test
  @DisplayName("상품을 아이디로 조회할 수 있다.")
  void testFindById() {
    var product = productJdbcRepository.findById(newProduct.getProductId());
    assertThat(product.isEmpty(), is(false));
  }

  @Test
  @DisplayName("상품을 카테고리로 조회할 수 있다.")
  void testFindByCategory() {
    var product = productJdbcRepository.findByCategory(Category.COFFEE_BEAN_PACKAGE);
    assertThat(product.isEmpty(), is(false));
  }

  @Test
  @DisplayName("상품을 수정할 수 있다.")
  void testUpdate() {
    productJdbcRepository.deleteAll();
    newProduct.setProductName("updated-product");
    productJdbcRepository.update(newProduct);

    var product = productJdbcRepository.findById(newProduct.getProductId());
    assertThat(product.isEmpty(), is(false));
    assertThat(product.get(), samePropertyValuesAs(newProduct));
  }

  @Test
  @DisplayName("상품을 전체 삭제한다.")
  void testDeleteAll() {
    productJdbcRepository.deleteAll();
    var all = productJdbcRepository.findAll();
    assertThat(all.isEmpty(), is(false));
  }
}
