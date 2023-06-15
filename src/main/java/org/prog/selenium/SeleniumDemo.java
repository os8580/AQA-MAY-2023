package org.prog.selenium;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumDemo {

  public static void main(String[] args) {
    WebDriver driver = null;
    try {
      driver = new ChromeDriver();

      driver.get("https://google.com/");


      WebElement searchBar = driver.findElement(By.name("q"));
      searchBar.sendKeys("Elon Musk");
      searchBar.sendKeys(Keys.ENTER);

      List<WebElement> searchResults = new WebDriverWait(driver, Duration.ofSeconds(5L))
          .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.partialLinkText("Elon Musk")));

      if (searchResults.isEmpty()) {
        throw new RuntimeException("No search results were found!");
      }
    } finally {
      if (driver != null) {
        driver.quit();
      }
    }
  }
}
