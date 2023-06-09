package org.testdouble;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputFile {
  private static final Digit Zero = new Digit('_', '|', ' ', '|', '|', '_', '|');
  private static final Digit One = new Digit(' ', ' ', ' ', '|', ' ', ' ', '|');
  private static final Digit Two = new Digit('_', ' ', '_', '|', '|', '_', ' ');
  private static final Digit Three = new Digit('_', ' ', '_', '|', ' ', '_', '|');
  private static final Digit Four = new Digit(' ', '|', '_', '|', ' ', ' ', '|');
  private static final Digit Five = new Digit('_', '|', '_', ' ', ' ', '_', '|');
  private static final Digit Six = new Digit('_', '|', '_', ' ', '|', '_', '|');
  private static final Digit Seven = new Digit('_', ' ', ' ', '|', ' ', ' ', '|');
  private static final Digit Eight = new Digit('_', '|', '_', '|', '|', '_', '|');
  private static final Digit Nine = new Digit('_', '|', '_', '|', ' ', '_', '|');

  private static final HashMap<Digit, String> digitStringHashMap;
  private static final int LINES_PER_ACCOUNT_NUMBER = 4;

  static {
    digitStringHashMap = new HashMap<>();
    digitStringHashMap.put(Zero, "0");
    digitStringHashMap.put(One, "1");
    digitStringHashMap.put(Two, "2");
    digitStringHashMap.put(Three, "3");
    digitStringHashMap.put(Four, "4");
    digitStringHashMap.put(Five, "5");
    digitStringHashMap.put(Six, "6");
    digitStringHashMap.put(Seven, "7");
    digitStringHashMap.put(Eight, "8");
    digitStringHashMap.put(Nine, "9");
  }

  private final List<String> lines;

  public InputFile(List<String> lines) {
    this.lines = lines;
  }

  public List<String> toAccountNumbers() {
    return IntStream.range(0, (lines.size() + 1) / LINES_PER_ACCOUNT_NUMBER)
            .mapToObj(this::toSingleAccountNumber)
            .collect(Collectors.toList());
  }

  private String toSingleAccountNumber(int index) {
    final int i = index * LINES_PER_ACCOUNT_NUMBER;
    final String firstLine = lines.get(i);
    final String secondLine = lines.get(i + 1);
    final String thirdLine = lines.get(i + 2);
    return IntStream.range(0, 9)
            .mapToObj(digit -> singleDigit(firstLine, secondLine, thirdLine, digit))
            .collect(Collectors.joining());
  }

  private String singleDigit(String firstLine, String secondLine, String thirdLine, int digit) {
    final char top = getCharAt(firstLine, 1, digit);

    final char leftTop = getCharAt(secondLine, 0, digit);
    final char middle = getCharAt(secondLine, 1, digit);
    final char rightTop = getCharAt(secondLine, 2, digit);

    final char leftBottom = getCharAt(thirdLine, 0, digit);
    final char bottom = getCharAt(thirdLine, 1, digit);
    final char rightBottom = getCharAt(thirdLine, 2, digit);

    return digitStringHashMap.getOrDefault(
            new Digit(top, leftTop, middle, rightTop, leftBottom, bottom, rightBottom),
            Constants.ILLEGIBLE_CHARACTER + "");
  }

  private char getCharAt(String firstLine, int index, int character) {
    final int i = character * 3 + index;
    return firstLine.length() > i ? firstLine.charAt(i) : ' ';
  }

  private record Digit(
          char top,
          char leftTop, char middle, char rightTop,
          char leftBottom, char bottom, char rightBottom
  ) {

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
      return toString().hashCode();
    }
  }

}
