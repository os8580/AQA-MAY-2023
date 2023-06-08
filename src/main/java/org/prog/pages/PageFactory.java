package org.prog.pages;

import org.openqa.selenium.WebDriver;

public class PageFactory {

  private final WebDriver driver;

  public PageFactory(WebDriver driver) {
    this.driver = driver;
  }

  public AbstractPage get(Pages pages) {
    switch (pages) {
      case GOOGLE:
        return new GooglePage(driver);
      case ROZETKA:
        return new RozetkaPage(driver);
      default:
        throw new RuntimeException();
    }
  }

  public void tearDown() {
    driver.quit();
  }
}
