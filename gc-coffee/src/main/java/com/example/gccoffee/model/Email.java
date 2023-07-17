package com.example.gccoffee.model;

import org.springframework.util.Assert;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {
  private final String address;

  public Email(String address) {
    Assert.notNull(address, "address should noe be null");
    Assert.isTrue(address.length() >= 4 && address.length() <= 50, "address length must be between 4 and 50 characters.");
    Assert.isTrue(checkAddress(address), "Invalid email address");
    this.address = address;
  }

  private static boolean checkAddress(String address) {
    return Pattern.matches("^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", address);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Email email)) return false;
    return address.equals(email.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address);
  }

  @Override
  public String toString() {
    return "Email{" +
            "address='" + address + '\'' +
            '}';
  }

  public String getAddress() {
    return address;
  }

}
