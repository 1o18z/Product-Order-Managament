package com.example.gccoffee.repository;

import com.example.gccoffee.dto.product.ProductUpdateRequest;
import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Product;
import com.example.gccoffee.query.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.RowMapper;

import java.util.*;
import java.util.List;

@Repository
public class ProductJdbcRepository implements ProductRepository {

  private final NamedParameterJdbcTemplate jdbcTemplate;
  private final InsertQuery insertQuery = new InsertQuery();
  private final UpdateQuery updateQuery = new UpdateQuery();
  private final DeleteQuery deleteQuery = new DeleteQuery();
  private final SelectQuery selectQuery = new SelectQuery();

  public ProductJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Product> findAll() {
    return jdbcTemplate.query(selectQuery
                    .select("*")
                    .from("products")
                    .getResult(),
            productRowMapper);
  }

  @Override
  public Product save(Product product) {
    SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
            .addValue("productId", product.getProductId().toString())
            .addValue("productName", product.getProductName())
            .addValue("category", product.getCategory().toString())
            .addValue("price", product.getPrice()
            );
    jdbcTemplate.update(insertQuery
                    .insert("products(product_id, product_name, category, price) ")
                    .values(":productId, :productName, :category, :price")
                    .getResult(),
            sqlParameterSource);
    return product;
  }

  @Override
  public Product update(ProductUpdateRequest productUpdateDto) {
    SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
            .addValue("productId", productUpdateDto.productId().toString())
            .addValue("productName", productUpdateDto.productName())
            .addValue("category", productUpdateDto.category().toString())
            .addValue("price", productUpdateDto.price()
            );
    jdbcTemplate.update(updateQuery
                    .update("products")
                    .set("product_name = :productName, category = :category, price = :price")
                    .where("product_id = :productId")
                    .getResult(),
            sqlParameterSource
    );
    return findById(productUpdateDto.productId()).get();
  }

  @Override
  public Optional<Product> findById(UUID productId) {
    try {
      return Optional.ofNullable(
              jdbcTemplate.queryForObject(selectQuery
                              .select("*")
                              .from("products")
                              .where("product_id = :productId")
                              .getResult(),
                      Collections.singletonMap("productId", productId), productRowMapper)
      );
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Product> findByName(String productName) {
    try {
      return Optional.ofNullable(
              jdbcTemplate.queryForObject(selectQuery
                              .select("*")
                              .from("products")
                              .where("product_name = :productName")
                              .getResult(),
                      Collections.singletonMap("productName", productName), productRowMapper)
      );
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public List<Product> findByCategory(Category category) {
    return jdbcTemplate.query(selectQuery
                    .select("*")
                    .from("products")
                    .where("category = :category")
                    .getResult(),
            Collections.singletonMap("category", category.toString()),
            productRowMapper
    );
  }

  @Override
  public void deleteAll() {
    jdbcTemplate.update(deleteQuery
                    .delete("products")
                    .getResult(),
            Collections.emptyMap());
  }

  @Override
  public void deleteById(UUID productId) {
    SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
            .addValue("productId", productId.toString());

    jdbcTemplate.update(deleteQuery
                    .delete("products")
                    .where("product_id = :productId")
                    .getResult(),
            sqlParameterSource);
  }

  private final RowMapper<Product> productRowMapper = (resultSet, rowNum) -> {
    Product product = new Product(
            UUID.fromString(resultSet.getString("product_id")),
            resultSet.getString("product_name"),
            Category.valueOf(resultSet.getString("category")),
            resultSet.getInt("price")
    );
    return product;
  };

}
