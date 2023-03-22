package org.testdouble;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountNumberTest {

  @Test
  public void isValid() {
    final String validValue= "000000051";
    assertEquals(validValue, new AccountNumber(validValue).toString());
  }

  @Test
  public void notValid() {
    final String invalidValue = "000000057";
    assertEquals(invalidValue + " ERR", new AccountNumber(invalidValue).toString());
  }

  @Test
  public void illegible() {
    final String invalidValue = "1234?678?";
    assertEquals(invalidValue + " ILL", new AccountNumber(invalidValue).toString());
  }
}
