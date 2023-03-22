package org.testdouble;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

public class InputFileTest {
  @Test
  public void parse() {
    final String input = """
             _
            | |
            |_|

                                    """;
    final List<AccountNumber> accountNumbers = new InputFile(Arrays.stream(input.split(System.lineSeparator())).toList()).parse();
    assertLinesMatch(Collections.singletonList("0"), accountNumbers.stream().map(Object::toString).collect(Collectors.toList()));
  }
}
