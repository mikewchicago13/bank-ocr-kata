package org.testdouble;

import java.util.stream.IntStream;

public class AccountNumber {
  private static final short ASCII_ZERO = 48;
  private final String toString;

  public AccountNumber(String toString) {
    this.toString = toString;
  }

  private int toDigit(int index) {
    return toString.charAt(index) - ASCII_ZERO;
  }

  @Override
  public String toString() {
    final int length = toString.length();
    final boolean isIllegible = IntStream.range(0, length)
            .mapToObj(x -> Constants.ILLEGIBLE_CHARACTER == toString.charAt(x))
            .reduce((a, b) -> a || b)
            .orElse(false);
    if (isIllegible) {
      return toString + " ILL";
    }

    final int checksum = IntStream.range(0, length)
            .map(index -> (length - index) * toDigit(index))
            .reduce(Integer::sum)
            .orElse(-1) % 11;
    final boolean isValid = checksum == 0;

    if (isValid) {
      return toString;
    }
    return toString + " ERR";
  }
}
