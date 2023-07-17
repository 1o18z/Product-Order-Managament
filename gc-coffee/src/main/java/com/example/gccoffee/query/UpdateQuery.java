package com.example.gccoffee.query;

import org.springframework.stereotype.Component;

@Component
public class UpdateQuery {

  private final StringBuilder stringBuilder = new StringBuilder();

  public UpdateQuery update(String table) {
    stringBuilder.append("UPDATE " + table);
    return this;
  }

  public UpdateQuery set(String sql){
    stringBuilder.append(" SET " + sql);
    return this;
  }

  public WhereQuery where(String condition){
    return new WhereQuery(this.stringBuilder.toString()).where(condition);
  }

}
