package org.testdouble;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class InputFile {
  private static final Digit Zero = new Digit('_', '|', ' ', '|', '|', '_', '|');
  private static final Digit One = new Digit(' ', ' ', ' ', '|', ' ', ' ', '|');
  private static final Digit Two = new Digit('_', ' ', '_', '|', '|', '_', ' ');

  private static final HashMap<Digit, String> digitStringHashMap;

  static {
    digitStringHashMap = new HashMap<>();
    digitStringHashMap.put(Zero, "0");
    digitStringHashMap.put(One, "1");
    digitStringHashMap.put(Two, "2");
  }

  private final String digitRepresentation;

  public InputFile(List<String> lines) {
    final String firstLine = lines.get(0);
    final char top = getCharAt(firstLine, 1);

    final String secondLine = lines.get(1);
    final char leftTop = getCharAt(secondLine, 0);
    final char middle = getCharAt(secondLine, 1);
    final char rightTop = getCharAt(secondLine, 2);

    final String thirdLine = lines.get(2);
    final char leftBottom = getCharAt(thirdLine, 0);
    final char bottom = getCharAt(thirdLine, 1);
    final char rightBottom = getCharAt(thirdLine, 2);

    final Digit digit = new Digit(top, leftTop, middle, rightTop, leftBottom, bottom, rightBottom);
    digitRepresentation = digitStringHashMap.getOrDefault(digit, "?");
  }

  private char getCharAt(String firstLine, int index) {
    return firstLine.length() > index ? firstLine.charAt(index) : ' ';
  }

  public List<AccountNumber> parse() {
    return Collections.singletonList(new AccountNumber(digitRepresentation));
  }

  private static class Digit {
    private final char top;
    private final char leftTop;
    private final char middle;
    private final char rightTop;
    private final char leftBottom;
    private final char bottom;
    private final char rightBottom;

    public Digit(char top, char leftTop, char middle, char rightTop, char leftBottom, char bottom, char rightBottom) {
      this.top = top;
      this.leftTop = leftTop;
      this.middle = middle;
      this.rightTop = rightTop;
      this.leftBottom = leftBottom;
      this.bottom = bottom;
      this.rightBottom = rightBottom;
    }

    @Override
    public String toString() {
      return " " + top + " " + System.lineSeparator() +
              leftTop + middle + rightTop + System.lineSeparator() +
              leftBottom + bottom + rightBottom + System.lineSeparator();
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof final Digit digit)) return false;
      return this.toString().equals(digit.toString());
    }

    @Override
    public int hashCode() {
      return Objects.hash(top, leftTop, middle, rightTop, leftBottom, bottom, rightBottom);
    }
  }

}
