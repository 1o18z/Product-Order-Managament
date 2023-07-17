package com.example.gccoffee.query;

import org.springframework.stereotype.Component;

@Component
public class WhereQuery {

  private final StringBuilder stringBuilder = new StringBuilder();

  public WhereQuery(String sql) {
    stringBuilder.append(sql + " WHERE ");
  }

  public WhereQuery where(String condition) {
    stringBuilder.append(condition);
    return this;
  }

  public String getResult() {
    String result = stringBuilder.toString();
    stringBuilder.setLength(0);
    return result;
  }

}
