package com.example.gccoffee.validator;

import com.example.gccoffee.model.Order;
import com.example.gccoffee.model.Product;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Component
public class OrderValidator {

  private static final String REG_EXP_EMAIL = "\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b";
  private static final Pattern EMAIL_PATTERN = Pattern.compile(REG_EXP_EMAIL);

  private static final int MINUMUM_QUANTITY = 1;
  private static final int MAXIMUM_QUANTITY = 50;

  private static final String INVALID_ID = "유효하지 않은 ID - ID가 비어있습니다.";
  private static final String INVALID_EMAIL = "이메일 형식이 맞지 않습니다.";
  private static final String INVALID_QUANTITY = "1 ~ 50 범위 내의 값을 입력하세요.";
  private static final String INVALID_ORDER = "존재하지 않는 주문입니다.";

  public static void checkExist(Optional<Order> order) {
    if (order.isEmpty()) {
      throw new NoSuchElementException("No Exist");
    }
  }

  public static void validId(UUID productId) {
    if (productId == null) {
      throw new IllegalArgumentException(INVALID_ID);
    }
  }

  public void validProduct(Optional<Product> product) {
    if (product.isEmpty()) {
      throw new NoSuchElementException("No Exist");
    }
  }

  public void validEmail(String email) {
    if (!EMAIL_PATTERN.matcher(email).matches() || email.isEmpty() || email.isBlank()) {
      throw new IllegalArgumentException(INVALID_EMAIL);
    }
  }

  public void validQuantity(int quantity) {
    if (quantity < MINUMUM_QUANTITY && quantity > MAXIMUM_QUANTITY) {
      throw new RuntimeException(INVALID_QUANTITY);
    }
  }

  public void validOrder(Optional<Order> order) {
    if (order.isEmpty()) {
      throw new NoSuchElementException(INVALID_ORDER);
    }
  }

}
