package org.testdouble;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutputFileTest {
  @Test
  public void toStringIsSimpleLineConcatenation() {
    final String actual = new OutputFile(Arrays.asList(new AccountNumber("1"), new AccountNumber("2"))).toString();
    assertEquals("1" + System.lineSeparator() + "2", actual);
  }
}
