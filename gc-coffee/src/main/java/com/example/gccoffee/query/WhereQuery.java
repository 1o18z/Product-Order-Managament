package com.example.gccoffee.query;

import static com.example.gccoffee.query.KeyWord.WHERE;

public class WhereQuery {

  private final StringBuilder stringBuilder = new StringBuilder();

  public WhereQuery(String sql) {
    stringBuilder
            .append(sql)
            .append(WHERE);
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
