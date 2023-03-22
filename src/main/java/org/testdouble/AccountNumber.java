package org.testdouble;

import java.util.stream.IntStream;

public class AccountNumber {
  private static final short ASCII_ZERO = 48;
  private final String toString;
  private final boolean isValid;

  public AccountNumber(String toString) {
    this.toString = toString;
    final int length = toString.length();
    final int checksum = IntStream.range(0, length)
            .map(index -> (length - index) * toDigit(index))
            .reduce(Integer::sum)
            .orElse(-1) % 11;
    isValid = checksum == 0;
  }

  private int toDigit(int index) {
    return toString.charAt(index) - ASCII_ZERO;
  }

  @Override
  public String toString() {
    return isValid ? toString : toString + " ERR";
  }
}
