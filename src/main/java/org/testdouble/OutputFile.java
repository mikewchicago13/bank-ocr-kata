package org.testdouble;

import java.util.List;
import java.util.stream.Collectors;

public class OutputFile {
  private final List<AccountNumber> accountNumbers;

  public OutputFile(List<AccountNumber> accountNumbers) {
    this.accountNumbers = accountNumbers;
  }

  @Override
  public String toString() {
    return accountNumbers.stream().map(AccountNumber::toString).collect(Collectors.joining(System.lineSeparator()));
  }
}
