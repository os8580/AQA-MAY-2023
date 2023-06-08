package org.prog.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

  protected WebDriver driver;
  private final String URL;

  public AbstractPage(WebDriver driver, String URL) {
    this.driver = driver;
    this.URL = URL;
  }

  public void loadPage() {
    driver.get("about:blank");
    driver.get(URL);
  }

  public boolean isPageLoaded() {
    return new WebDriverWait(driver, Duration.ofSeconds(45L)).until(ExpectedConditions.urlToBe(URL));
  }
}
