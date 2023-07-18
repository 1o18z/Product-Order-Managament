package com.example.gccoffee.query;

import org.springframework.stereotype.Component;

import static com.example.gccoffee.query.KeyWord.SET;
import static com.example.gccoffee.query.KeyWord.UPDATE;

@Component
public class UpdateQuery {

  private final StringBuilder stringBuilder = new StringBuilder();

  public UpdateQuery update(String table) {
    stringBuilder
            .append(UPDATE)
            .append(table);
    return this;
  }

  public UpdateQuery set(String sql) {
    stringBuilder
            .append(SET)
            .append(sql);
    return this;
  }

  public WhereQuery where(String condition) {
    return new WhereQuery(this.stringBuilder.toString()).where(condition);
  }

}
