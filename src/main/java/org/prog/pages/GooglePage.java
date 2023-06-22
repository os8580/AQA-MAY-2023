package org.prog.pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GooglePage extends AbstractPage {

  private static final String URL = "https://google.com/";

  public GooglePage(WebDriver driver) {
    super(driver, URL);
    this.driver = driver;
  }

  public void acceptCookiesIfPresent() {
    List<WebElement> buttons = new WebDriverWait(driver, Duration.ofSeconds(5L))
        .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("button")));

    if (buttons.size() == 5) {
      buttons.get(3).click();
    }
  }

  public void setSearchValue(String value) {
    driver.findElement(By.name("q")).sendKeys(value);
  }

  public void performSearch() {
    driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
  }

  public List<WebElement> getSearchResults() {
    return new WebDriverWait(driver, Duration.ofSeconds(5L))
        .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h3/..")))
        .stream()
        .filter(WebElement::isDisplayed)
        .collect(Collectors.toList());
  }

  public void waitForSearchSuggestions() {
    new WebDriverWait(driver, Duration.ofSeconds(2L))
        .until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//*[@role='listbox']")
        ));
  }
}
