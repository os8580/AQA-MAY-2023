package org.prog;

import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JunitTests {

  private static WebDriver driver;

  @BeforeAll
  public static void setUp() {
    driver = new ChromeDriver();
  }

  @BeforeEach
  public void preTest() {
    driver.get("https://google.com/");
    acceptCookiesIfPresent();
  }

  @ParameterizedTest
  @ValueSource(strings = {"Elon Musk", "Jimmy Hendrix", "Margot Robbie"})
  public void whoIsMusk(String celebrityName) {
    search(celebrityName);
    List<WebElement> searchResults = getSearchResultsWithText(celebrityName);

    Assertions.assertTrue(searchResults.size() > 1, "More than one link with searched text expected!");
  }

//  @Test
//  public void whoIsHendirx() {
//    search("Jimmy Hendrix");
//    List<WebElement> searchResults = getSearchResultsWithText("Jimmy Hendrix");
//
//    Assertions.assertTrue(searchResults.size() > 1, "More than one link with searched text expected!");
//  }
//
//  @Test
//  public void whoIsMargo() {
//    search("Margot Robbie");
//    List<WebElement> searchResults = getSearchResultsWithText("Margot Robbie");
//
//    Assertions.assertTrue(searchResults.size() > 1, "More than one link with searched text expected!");
//  }

  @AfterEach
  public void postTest() {
    driver.get("about:blank");
  }

  @AfterAll
  public static void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

  private List<WebElement> getSearchResultsWithText(String value) {
    return new WebDriverWait(driver, Duration.ofSeconds(5L))
        .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.partialLinkText(value)));
  }

  private void search(String searchValue) {
    WebElement searchBar = driver.findElement(By.name("q"));
    searchBar.sendKeys(searchValue);
    searchBar.sendKeys(Keys.ENTER);
  }

  private void acceptCookiesIfPresent() {
    List<WebElement> buttons = new WebDriverWait(driver, Duration.ofSeconds(5L))
        .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("button")));

    if (buttons.size() == 6) {
      buttons.get(3).click();
    }
  }
}
