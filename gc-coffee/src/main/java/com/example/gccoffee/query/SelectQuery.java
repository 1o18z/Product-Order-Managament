package com.example.gccoffee.query;

import org.springframework.stereotype.Component;

import static com.example.gccoffee.query.KeyWord.FROM;
import static com.example.gccoffee.query.KeyWord.SELECT;

@Component
public class SelectQuery {

  private final StringBuilder stringBuilder = new StringBuilder();

  public SelectQuery select(String sql) {
    stringBuilder
            .append(SELECT)
            .append(sql);
    return this;
  }

  public SelectQuery from(String table) {
    stringBuilder
            .append(FROM)
            .append(table);
    return this;
  }

  public WhereQuery where(String condition) {
    return new WhereQuery(this.stringBuilder.toString()).where(condition);
  }

  public String getResult() {
    String result = stringBuilder.toString();
    stringBuilder.setLength(0);
    return result;
  }

}
