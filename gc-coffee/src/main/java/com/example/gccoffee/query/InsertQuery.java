package com.example.gccoffee.query;

import org.springframework.stereotype.Component;

@Component
public class InsertQuery {

  private final StringBuilder stringBuilder = new StringBuilder();

  public InsertQuery insert(String sql) {
    stringBuilder.append("INSERT INTO " + sql);
    return this;
  }

  public InsertQuery values(String condition) {
    stringBuilder.append(" VALUES(" + condition + ")");
    return this;
  }

  public String getResult() {
    String result = stringBuilder.toString();
    stringBuilder.setLength(0);
    return result;
  }

}
