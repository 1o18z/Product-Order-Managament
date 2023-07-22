package com.example.gccoffee.repository;

import com.example.gccoffee.model.Order;
import com.example.gccoffee.model.OrderStatus;
import com.example.gccoffee.query.DeleteQuery;
import com.example.gccoffee.query.InsertQuery;
import com.example.gccoffee.query.SelectQuery;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderJdbcRepository implements OrderRepository {

  private final NamedParameterJdbcTemplate jdbcTemplate;
  private final InsertQuery insertQuery = new InsertQuery();
  private final DeleteQuery deleteQuery = new DeleteQuery();
  private final SelectQuery selectQuery = new SelectQuery();

  public OrderJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Order save(Order order) {
    SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
            .addValue("orderId", order.getOrderId().toString())
            .addValue("email", order.getEmail())
            .addValue("address", order.getAddress())
            .addValue("orderStatus", order.getOrderStatus().name()
            );
    jdbcTemplate.update(insertQuery
                    .insert("orders(order_id, email, address, order_status)")
                    .values(":orderId, :email, :address, :orderStatus")
                    .getResult(),
            sqlParameterSource);
    return order;
  }

  @Override
  public List<Order> findAll() {
    return jdbcTemplate.query(selectQuery
                    .select("*")
                    .from("orders")
                    .getResult(),
            orderRowMapper);
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
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public void cancel(UUID orderId) {
    jdbcTemplate.update(deleteQuery
                    .delete("orders")
                    .where("order_id = :orderId")
                    .getResult(),
            Map.of("orderId", orderId)
    );
  }

  @Override
  public void deleteAll() {
    jdbcTemplate.update(deleteQuery
                    .delete("orders")
                    .getResult(),
            Collections.emptyMap());
  }

  private RowMapper<Order> orderRowMapper = (resultSet, rowNum) -> {
    Order order = new Order(
            UUID.fromString(resultSet.getString("order_id")),
            resultSet.getString("email"),
            resultSet.getString("address"),
            OrderStatus.valueOf(resultSet.getString("order_status"))
    );
    return order;
  };

}
