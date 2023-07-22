package com.example.gccoffee.repository;

import com.example.gccoffee.model.OrderItem;
import com.example.gccoffee.query.DeleteQuery;
import com.example.gccoffee.query.InsertQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class OrderItemJdbcRepository implements OrderItemRepository {

  private final NamedParameterJdbcTemplate jdbcTemplate;
  private final InsertQuery insertQuery = new InsertQuery();
  private final DeleteQuery deleteQuery = new DeleteQuery();

  public OrderItemJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public UUID save(List<OrderItem> orderItems) {
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

  @Override
  public void deleteOrderItems(UUID orderId){
    jdbcTemplate.update(deleteQuery
                    .delete("order_items")
                    .where("order_id = :orderId")
                    .getResult(),
            Map.of("orderId", orderId)
    );
  }
  @Override
  public void deleteAllOrderItems() {
    jdbcTemplate.update(deleteQuery
                    .delete("order_items")
                    .getResult(),
            Collections.emptyMap());
  }

}

