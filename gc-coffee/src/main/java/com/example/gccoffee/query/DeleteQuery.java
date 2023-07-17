package com.example.gccoffee.query;

import org.springframework.stereotype.Component;

@Component
public class DeleteQuery {

  private final StringBuilder stringBuilder = new StringBuilder();

  public DeleteQuery delete(String sql) {
    stringBuilder.append("DELETE FROM " + sql);
    return this;
  }

  public WhereQuery where() {
    return new WhereQuery(this.stringBuilder.toString());
  }

  public String getResult() {
    String result = stringBuilder.toString();
    stringBuilder.setLength(0);
    return result;
  }

}
