package com.example.gccoffee.repository;

import com.example.gccoffee.model.OrderItem;
import com.example.gccoffee.query.InsertQuery;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class OrderItemJdbcRepository implements OrderItemRepository {

  private final NamedParameterJdbcTemplate jdbcTemplate;
  private final InsertQuery insertQuery = new InsertQuery();

  public OrderItemJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public UUID add(List<OrderItem> orderItems) {
    SqlParameterSource[] sqlParameterSources = new SqlParameterSource[orderItems.size()];
    int idx = 0;
    for (OrderItem orderItem : orderItems) {
      SqlParameterSource source = new MapSqlParameterSource()
              .addValue("orderItemId", orderItem.getOrderItemId().toString())
              .addValue("orderId", orderItem.getOrderId().toString())
              .addValue("productId", orderItem.getProductId().toString())
              .addValue("quantity", orderItem.getQuantity()
              );
      sqlParameterSources[idx++] = source;
    }

    jdbcTemplate.batchUpdate(insertQuery
                    .insert("order_items(order_item_id, order_id, product_id, quantity)")
                    .values(":orderItemId, :orderId, :productId, :quantity")
                    .getResult(),
            sqlParameterSources);
    return orderItems.get(0).getOrderItemId();
  }

  private final RowMapper<OrderItem> orderItemRowMapper = (resultSet, rowNum) -> {

    OrderItem orderItem = new OrderItem(
            UUID.fromString(resultSet.getString("order_item_id")),
            UUID.fromString(resultSet.getString("order_id")),
            UUID.fromString(resultSet.getString("product_id")),
            resultSet.getInt("quantity")
    );
    return orderItem;
  };

}
