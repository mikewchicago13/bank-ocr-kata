package org.testdouble;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

public class InputFileTest {
  @ParameterizedTest
  @EnumSource(Samples.class)
  public void parse(Samples digits) {
    final String input = digits.getInput();
    final String[] split = input.split(System.lineSeparator());
    final List<String> lines = Arrays.stream(split).toList();
    assertLinesMatch(digits.getExpected(), new InputFile(lines).toAccountNumbers());
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
    IllegibleMissing_TopLeftPipeOn5_and_BottomRightPipeOn9("""
                _  _     _  _  _  _  _\s
              | _| _||_| _ |_   ||_||_|
              ||_  _|  | _||_|  ||_| _\s
                        
            """, "1234?678?"),
    IllegibleMissing_TopUnderscoreOn0("""
                _  _  _  _  _  _  _  _\s
            | || || || || || || || || |
            |_||_||_||_||_||_||_||_||_|
            
            """, "?00000000"),
    IllegibleMissing_TopRightPipeOn0("""
             _  _  _  _  _  _  _  _  _\s
            |  | || || || || || || || |
            |_||_||_||_||_||_||_||_||_|
            
            """, "?00000000"),
    IllegibleMissing_BottomRightPipeOn0("""
             _  _  _  _  _  _  _  _  _\s
            | || || || || || || || || |
            |_ |_||_||_||_||_||_||_||_|
            
            """, "?00000000"),
    IllegibleMissing_BottomUnderscoreOn0("""
             _  _  _  _  _  _  _  _  _\s
            | || || || || || || || || |
            | ||_||_||_||_||_||_||_||_|
            
            """, "?00000000"),
    IllegibleMissing_BottomLeftPipeOn0("""
             _  _  _  _  _  _  _  _  _\s
            | || || || || || || || || |
             _||_||_||_||_||_||_||_||_|
            
            """, "?00000000"),
    IllegibleMissing_TopLeftPipeOn0("""
             _  _  _  _  _  _  _  _  _\s
              || || || || || || || || |
            |_||_||_||_||_||_||_||_||_|
            
            """, "?00000000"),
    IllegibleMissing_TopRightPipeOn1("""
            
                 |  |  |  |  |  |  |  |
              |  |  |  |  |  |  |  |  |
                        
            """, "?11111111"),
    IllegibleMissing_BottomRightPipeOn1("""
            
              |  |  |  |  |  |  |  |  |
                 |  |  |  |  |  |  |  |
                        
            """, "?11111111"),
    IllegibleMissing_EverythingOnFirstDigit("""
            
                 |  |  |  |  |  |  |  |
                 |  |  |  |  |  |  |  |
                        
            """, "?11111111"),
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
