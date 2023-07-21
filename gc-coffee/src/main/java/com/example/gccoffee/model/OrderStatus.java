package com.example.gccoffee.model;

public enum OrderStatus {
  BASKET, // 장바구니
  ORDER_SUCCESS, // 주문 성공
  PAYMENT_CONFIRMED, // 결제 완료
  READY_FOR_DELIVERY, // 상품 준비 중
  SHIPPED, // 배송 중
  DELIVERY_SUCCESS, // 배송 완료
  CANCELED,
  ;
}
