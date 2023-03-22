package org.testdouble;

import java.util.List;
import java.util.stream.Collectors;

public class OutputFile {
  private final List<String> accountNumbers;

  public OutputFile(List<String> accountNumbers) {
    this.accountNumbers = accountNumbers;
  }

  @Override
  public String toString() {
    return accountNumbers.stream().collect(Collectors.joining(System.lineSeparator()));
  }
}
