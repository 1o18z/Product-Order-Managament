package com.example.gccoffee.query;

import static com.example.gccoffee.query.KeyWord.*;

public class DeleteQuery {

  private final StringBuilder stringBuilder = new StringBuilder();

  public DeleteQuery delete(String table) {
    stringBuilder
            .append(DELETE)
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
