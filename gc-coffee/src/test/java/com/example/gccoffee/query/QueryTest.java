package com.example.gccoffee.query;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueryTest {

  @Test
  @DisplayName("UpdateQuery를 사용해 name을 변경하는 update 쿼리문 작성을 성공한다.")
  void updateTest() {
    String updateQuery = "UPDATE customers SET customer_name = :customerName WHERE email = :email";
    String result = new UpdateQuery()
            .update("customers")
            .set("customer_name = :customerName")
            .where("email = :email")
            .getResult();
    Assertions.assertEquals(updateQuery, result);
    System.out.println("result---> " + result);
  }

  @Test
  @DisplayName("InsertQuery를 사용해 insert 쿼리문 작성을 성공한다.")
  void insertTest() {
    String insertQuery = "INSERT INTO customers(customer_id, customer_name, email) VALUES(:customerId, :customerName, :email)";
    String result = new InsertQuery()
            .insert("customers(customer_id, customer_name, email)")
            .values(":customerId, :customerName, :email")
            .getResult();
    Assertions.assertEquals(insertQuery, result);
    System.out.println("result---> " + result);
  }

  @Test
  @DisplayName("DeleteQuery를 사용해 delete 쿼리문 작성을 성공한다.")
  void deleteTest() {
    String deleteQuery = "DELETE FROM customers";
    String result = new DeleteQuery()
            .delete("customers")
            .getResult();
    Assertions.assertEquals(deleteQuery, result);
    System.out.println("result---> " + result);
  }

  @Test
  @DisplayName("SelectQuery를 사용해 select 쿼리문 작성을 성공한다.")
  void selectTest() {
    String selectQuery = "SELECT * FROM customers WHERE customer_id = :customerId";
    String result = new SelectQuery()
            .select("*")
            .from("customers")
            .where("customer_id = :customerId")
            .getResult();
    Assertions.assertEquals(selectQuery, result);
    System.out.println("result---> " + result);
  }

}
