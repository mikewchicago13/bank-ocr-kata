package org.testdouble;

import java.util.stream.IntStream;

public class AccountNumber {
  private static final short ASCII_ZERO = 48;
  private final String toString;

  public AccountNumber(String toString) {
    this.toString = toString;
  }

  @Override
  public String toString() {
    if (isIllegible()) {
      return toString + " ILL";
    }

    if (isValid()) {
      return toString;
    }
    return toString + " ERR";
  }

  private boolean isIllegible() {
    final int length = toString.length();

    return IntStream.range(0, length)
            .anyMatch(x -> Constants.ILLEGIBLE_CHARACTER == toString.charAt(x));
  }

  private boolean isValid() {
    final int length = toString.length();

    final int checksum = IntStream.range(0, length)
            .map(index -> (length - index) * toDigit(index))
            .reduce(Integer::sum)
            .orElse(-1) % 11;

    return checksum == 0;
  }

  private int toDigit(int index) {
    return toString.charAt(index) - ASCII_ZERO;
  }
}
