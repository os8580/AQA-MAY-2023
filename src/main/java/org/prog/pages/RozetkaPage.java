package org.prog.pages;

import org.openqa.selenium.WebDriver;

public class RozetkaPage extends AbstractPage {

  private static final String URL = "https://rozetka.com.ua/";

  public RozetkaPage(WebDriver driver) {
    super(driver, URL);
    this.driver = driver;
  }
}
