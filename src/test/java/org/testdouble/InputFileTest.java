package org.testdouble;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

public class InputFileTest {
  @ParameterizedTest
  @EnumSource(SingleDigits.class)
  public void parse(SingleDigits digits) {
    final String input = digits.toString();
    final List<AccountNumber> accountNumbers = new InputFile(Arrays.stream(input.split(System.lineSeparator())).toList()).parse();
    assertLinesMatch(Collections.singletonList(digits.getExpected()), accountNumbers.stream().map(Object::toString).collect(Collectors.toList()));
  }

  private enum SingleDigits{
    Zero("""
             _
            | |
            |_|
            
            """, "0"),
    One("""
                
              |
              |
              
            """, "1"),
    Two("""
             _
             _|
            |_
            
            """, "2"),
    Three("""
             _
             _|
             _|
            
            """, "3"),
    Four("""
             
            |_|
              |
            
            """, "4"),
    Five("""
             _
            |_
             _|
            
            """, "5"),
    Six("""
             _
            |_
            |_|
            
            """, "6"),
    Seven("""
             _
              |
              |
            
            """, "7"),
    ;

    private final String input;
    private final String expected;

    SingleDigits(String input, String expected) {

      this.input = input;
      this.expected = expected;
    }

    public String getExpected() {
      return expected;
    }

    @Override
    public String toString() {
      return input;
    }
  }

}
