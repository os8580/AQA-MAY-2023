package org.prog;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertNG {

  @Test
  public void softAssertDemo() {
    SoftAssert softAssert = new SoftAssert();
    String stringUnderTest = "abc";

    softAssert.assertTrue(stringUnderTest.contains("a"), "No letter A detected!");
    softAssert.assertTrue(stringUnderTest.contains("f"), "No letter F detected!");
    softAssert.assertTrue(stringUnderTest.contains("j"), "No letter J detected!");
    softAssert.assertTrue(stringUnderTest.contains("c"), "No letter C detected!");

    softAssert.assertAll();
  }
}
