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
  @EnumSource(Samples.class)
  public void parse(Samples digits) {
    final String input = digits.getInput();
    final String[] split = input.split(System.lineSeparator());
    final List<String> lines = Arrays.stream(split).toList();
    final List<AccountNumber> accountNumbers = new InputFile(lines).toAccountNumbers();
    assertLinesMatch(digits.getExpected(), accountNumbers.stream().map(Object::toString).map(x -> x.substring(0, 9)).collect(Collectors.toList()));
  }

  private enum Samples {
    Zero("""
             _  _  _  _  _  _  _  _  _\s
            | || || || || || || || || |
            |_||_||_||_||_||_||_||_||_|
            
            """, "000000000"),
    One("""
            
              |  |  |  |  |  |  |  |  |
              |  |  |  |  |  |  |  |  |
                        
            """, "111111111"),
    Two("""
             _  _  _  _  _  _  _  _  _\s
             _| _| _| _| _| _| _| _| _|
            |_ |_ |_ |_ |_ |_ |_ |_ |_\s
            
            """, "222222222"),
    Three("""
             _  _  _  _  _  _  _  _  _\s
             _| _| _| _| _| _| _| _| _|
             _| _| _| _| _| _| _| _| _|
            
            """, "333333333"),
    Four("""
                                       
            |_||_||_||_||_||_||_||_||_|
              |  |  |  |  |  |  |  |  |
            
            """, "444444444"),
    Five("""
             _  _  _  _  _  _  _  _  _\s
            |_ |_ |_ |_ |_ |_ |_ |_ |_\s
             _| _| _| _| _| _| _| _| _|
            
            """, "555555555"),
    Six("""
             _  _  _  _  _  _  _  _  _\s
            |_ |_ |_ |_ |_ |_ |_ |_ |_\s
            |_||_||_||_||_||_||_||_||_|
            
            """, "666666666"),
    Seven("""
             _  _  _  _  _  _  _  _  _\s
              |  |  |  |  |  |  |  |  |
              |  |  |  |  |  |  |  |  |
            
            """, "777777777"),
    Eight("""
             _  _  _  _  _  _  _  _  _\s
            |_||_||_||_||_||_||_||_||_|
            |_||_||_||_||_||_||_||_||_|
            
            """, "888888888"),
    Nine("""
             _  _  _  _  _  _  _  _  _\s
            |_||_||_||_||_||_||_||_||_|
             _| _| _| _| _| _| _| _| _|
            
            """, "999999999"),
    DifferentNumbers("""
                _  _     _  _  _  _  _\s
              | _| _||_||_ |_   ||_||_|
              ||_  _|  | _||_|  ||_| _|
                        
            """, "123456789"),
    MultipleLines("""
                _  _     _  _  _  _  _\s
              | _| _||_||_ |_   ||_||_|
              ||_  _|  | _||_|  ||_| _|
                        
             _  _  _  _  _  _  _  _  _\s
            |_||_||_||_||_||_||_||_||_|
             _| _| _| _| _| _| _| _| _|
                        
            """, Arrays.asList("123456789", "999999999")),
    ;

    private final String input;
    private final List<String> expected;

    Samples(String input, String expected) {
      this(input, Collections.singletonList(expected));
    }
    Samples(String input, List<String> expected) {

      this.input = input;
      this.expected = expected;
    }

    @Override
    public String toString() {
      return this.name();
    }

    public String getInput() {
      return input;
    }

    public List<String> getExpected() {
      return expected;
    }
  }

}
