package org.prog;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class WebDriverNG {

  public static WebDriver driver;

  @BeforeSuite
  public void setupDriver() {
    driver = new ChromeDriver();
  }

  @AfterSuite
  public void tearDown() {
    driver.quit();
  }
}
