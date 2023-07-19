package com.example.gccoffee.query;

import static com.example.gccoffee.query.KeyWord.*;

public class InsertQuery {

  private final StringBuilder stringBuilder = new StringBuilder();

  public InsertQuery insert(String sql) {
    stringBuilder.setLength(0);
    stringBuilder
            .append(INSERT)
            .append(sql);
    return this;
  }

  public InsertQuery values(String condition) {
    stringBuilder
            .append(VALUES)
            .append(LEFT_BRACKET)
            .append(condition)
            .append(RIGHT_BRACKET);
    return this;
  }

  public String getResult() {
    String result = stringBuilder.toString();
    stringBuilder.setLength(0);
    return result;
  }

}
