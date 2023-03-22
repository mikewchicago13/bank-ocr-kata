package org.testdouble;

import java.util.Collections;
import java.util.List;

public class InputFile {
  public InputFile(List<String> lines) {
  }

  public List<AccountNumber> parse() {
    return Collections.singletonList(new AccountNumber());
  }
}
