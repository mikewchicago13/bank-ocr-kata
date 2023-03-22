package org.testdouble;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountNumberTest {

  @ParameterizedTest
  @ValueSource(strings = {"000000051"})
  public void isValid(String validValue) {
    assertEquals(validValue, new AccountNumber(validValue).toString());
  }

  @ParameterizedTest
  @ValueSource(strings = {"000000057"})
  public void notValid(String invalidValue) {
    assertEquals(invalidValue + " ERR", new AccountNumber(invalidValue).toString());
  }

  @Test
  public void illegible() {
    final String invalidValue = "1234?678?";
    assertEquals(invalidValue + " ILL", new AccountNumber(invalidValue).toString());
  }
}
