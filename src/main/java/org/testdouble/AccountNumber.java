package org.testdouble;

public class AccountNumber {
  private final String toString;

  public AccountNumber(String toString) {
    this.toString = toString;
  }

  @Override
  public String toString() {
    return toString;
  }
}
