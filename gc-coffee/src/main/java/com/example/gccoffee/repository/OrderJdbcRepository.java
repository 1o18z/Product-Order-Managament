package com.example.gccoffee.repository;

import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Order;
import com.example.gccoffee.model.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderJdbcRepository implements OrderRepository {

  private final NamedParameterJdbcTemplate jdbcTemplate;

  public OrderJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Order insert(Order order) {
    MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource()
            .addValue("orderId", order.getOrderId())
            .addValue("email", order.getEmail())
            .addValue("address", order.getAddress())
            .addValue("product", order.getProduct())
            .addValue("quantity", order.getQuantity())
            .addValue("orderStatus", order.getOrderStatus())
            .addValue("createdAt", order.getCreatedAt()
            );
    jdbcTemplate.update("INSERT INTO orders(order_id, email, address, product, quantity, orderStatus, created_at) " +
            "VALUES(:orderId, :email, :address, :product, :quantity, :orderStatus, :createdAt)", sqlParameterSource);
    return order;
  }

  @Override
  public List<Order> findAll() {
    List<Order> orders = jdbcTemplate.query("SELECT * FROM orders", orderRowMapper);
    return orders;
  }

  @Override
  public Optional<Order> findById(UUID orderId) {
    try {
      return Optional.ofNullable(
              jdbcTemplate.queryForObject("SELECT * FROM orders WHERE order_id = :orderId",
                      Map.of("orderId", orderId), orderRowMapper)
      );
    } catch (EmptyStackException e) {
      return Optional.empty();
    }
  }

  @Override
  public void cancel(UUID orderId) {
    jdbcTemplate.update("DELETE * from orders WHERE order_id = :orderId", Collections.emptyMap());
  }

  private RowMapper<Product> productRowMapper = (resultSet, rowNum) ->
          new Product(
                  UUID.fromString(resultSet.getString("productId")),
                  resultSet.getString("name"),
                  Category.valueOf(resultSet.getString("category")),
                  resultSet.getLong("price"),
                  resultSet.getString("description")
          );

  private RowMapper<Order> orderRowMapper = (resultSet, rowNum) -> {
    Product product = productRowMapper.mapRow(resultSet, rowNum);
    Order order = new Order(
            UUID.fromString(resultSet.getString("orderId")),
            resultSet.getString("email"),
            resultSet.getString("address"),
            product,
            resultSet.getInt("quantity")
    );
    return order;
  };

}
