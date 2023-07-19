package com.example.gccoffee.repository;

import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Order;
import com.example.gccoffee.model.Product;
import com.example.gccoffee.query.DeleteQuery;
import com.example.gccoffee.query.InsertQuery;
import com.example.gccoffee.query.SelectQuery;
import com.example.gccoffee.query.UpdateQuery;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderJdbcRepository implements OrderRepository {

  private final NamedParameterJdbcTemplate jdbcTemplate;
  private final InsertQuery insertQuery = new InsertQuery();
  private final UpdateQuery updateQuery = new UpdateQuery();
  private final DeleteQuery deleteQuery = new DeleteQuery();
  private final SelectQuery selectQuery = new SelectQuery();

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
    jdbcTemplate.update(insertQuery
                    .insert("orders(order_id, email, address, product, quantity, orderStatus, created_at)")
                    .values(":orderId, :email, :address, :product, :quantity, :orderStatus, :createdAt")
                    .getResult(),
            sqlParameterSource);
    return order;
  }

  @Override
  public List<Order> findAll() {
    List<Order> orders = jdbcTemplate.query(selectQuery
                    .select("*")
                    .from("orders")
                    .getResult(),
            orderRowMapper);
    return orders;
  }

  @Override
  public Optional<Order> findById(UUID orderId) {
    try {
      return Optional.ofNullable(
              jdbcTemplate.queryForObject(selectQuery
                              .select("*")
                              .from("orders")
                              .where("order_id = :orderId")
                              .getResult(),
                      Map.of("orderId", orderId), orderRowMapper)
      );
    } catch (EmptyStackException e) {
      return Optional.empty();
    }
  }

  @Override
  public void cancel(UUID orderId) {
    jdbcTemplate.update(deleteQuery
                    .delete("orders")
                    .where("order_id = :orderId")
                    .getResult(),
            Collections.emptyMap());
  }

  private RowMapper<Product> productRowMapper = (resultSet, rowNum) ->
          new Product(
                  UUID.fromString(resultSet.getString("productId")),
                  resultSet.getString("name"),
                  Category.valueOf(resultSet.getString("category")),
                  resultSet.getInt("price"),
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
