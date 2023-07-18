package com.example.gccoffee.repository;

import com.example.gccoffee.dto.product.ProductUpdateDto;
import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Product;
import com.example.gccoffee.query.SelectQuery;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.List;

@Repository
public class ProductJdbcRepository implements ProductRepository {

  private final NamedParameterJdbcTemplate jdbcTemplate;
  private final SelectQuery selectQuery;

  public ProductJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate, SelectQuery selectQuery) {
    this.jdbcTemplate = jdbcTemplate;
    this.selectQuery = selectQuery;
  }

  @Override
  public List<Product> findAll() {
    return jdbcTemplate.query("SELECT * FROM products", productRowMapper);
  }

  @Override
  public Product insert(Product product) {
    MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource()
            .addValue("productId", product.getProductId())
            .addValue("productName", product.getName())
            .addValue("category", product.getCategory().toString())
            .addValue("price", product.getPrice())
            .addValue("description", product.getDescription())
            .addValue("createdAt", product.getCreatedAt()
            );
    int insertedProduct = jdbcTemplate.update("INSERT INTO products(product_id, product_name, category, price, description, created_at) " +
            "VALUES(:productId, :productName, :category, :price, :description, :createdAt)", sqlParameterSource);
    if (insertedProduct != 1) {
      throw new RuntimeException("Nothing was inserted");
    }
    return product;
  }

  @Override
  public Product update(ProductUpdateDto productUpdateDto) {
    BeanPropertySqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(productUpdateDto);
    int updatedProduct = jdbcTemplate.update("UPDATE products SET product_name, category, price, description" +
            "WHERE product_id = :productId", sqlParameterSource
    );
    if (updatedProduct != 1) {
      throw new RuntimeException("Nothing was updated");
    }
    return findById(productUpdateDto.productId()).get();
  }

  @Override
  public Optional<Product> findById(UUID productId) {
    try {
      return Optional.ofNullable(
              jdbcTemplate.queryForObject("SELECT * FROM products WHERE product_id = :productId",
                      Collections.singletonMap("productId", productId.toString().getBytes()), productRowMapper)
      );
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Product> findByName(String productName) {
    try {
      return Optional.ofNullable(
              jdbcTemplate.queryForObject("SELECT * FROM products WHERE product_id = :productId",
                      Collections.singletonMap("productName", productName), productRowMapper)
      );
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public List<Product> findByCategory(Category category) {
    return jdbcTemplate.query("SELECT * FROM products WHERE category = :category",
            Collections.singletonMap("category", category),
            productRowMapper
    );
  }

  @Override
  public void deleteAll() {
    jdbcTemplate.update("DELETE * from products", Collections.emptyMap());
  }

  @Override
  public void deleteById(UUID productId) {
    jdbcTemplate.update("DELETE * from products WHERE product_id = :productId", Collections.emptyMap());
  }

  private static final RowMapper<Product> productRowMapper = (resultSet, rowNum) -> {
    Product product = new Product(
            UUID.fromString(resultSet.getString("productId")),
            resultSet.getString("productName"),
            Category.valueOf(resultSet.getString("category")),
            resultSet.getInt("price"),
            resultSet.getString("description")
    );
    return product;
  };

}
