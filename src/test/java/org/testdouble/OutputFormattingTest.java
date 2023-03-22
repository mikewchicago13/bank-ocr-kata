package org.testdouble;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutputFormattingTest {
  @Test
  public void toStringIsSimpleLineConcatenation() {
    final String actual = new OutputFormatting(Arrays.asList("1", "2")).toString();
    assertEquals("1" + System.lineSeparator() + "2", actual);
  }
}
