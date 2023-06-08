package org.prog;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNGTests {

  @BeforeMethod
  public void loadGoogle() {
    WebDriverNG.driver.get("https://google.com/");
    acceptCookiesIfPresent();
  }

  @AfterMethod
  public void loadBlankPage() {
    WebDriverNG.driver.get("about:blank");
  }

  @Test(dataProvider = "test1")
  public void myNGTest(String celebrityName, int amount) {
    search(celebrityName);
    List<WebElement> searchResults = getSearchResultsWithText(celebrityName);

    Assert.assertTrue(searchResults.size() > amount, "More than one link with searched text expected!");
  }

  @DataProvider(name = "test1")
  public static Object[][] primeNumbers() {
    return new Object[][] {
        {"Elon Musk", 1},
        {"Margot Robbie", 1},
        {"Merlin Monroe", 1},
        {"Merlin Manson", 1},
        {"Jimi Hendrix", 1}
    };
  }

  private List<WebElement> getSearchResultsWithText(String value) {
    List<WebElement> elements = new WebDriverWait(WebDriverNG.driver, Duration.ofSeconds(5L))
        .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h3/..")));

    List<WebElement> searchLinks = new ArrayList<>();

    for (WebElement e : elements) {
      if (e.isDisplayed()) {
        searchLinks.add(e);
      }
    }

    return searchLinks;
  }

  private void search(String searchValue) {
    WebElement searchBar = WebDriverNG.driver.findElement(By.name("q"));
    searchBar.sendKeys(searchValue);
    searchBar.sendKeys(Keys.ENTER);
  }

  private void acceptCookiesIfPresent() {
    List<WebElement> buttons = new WebDriverWait(WebDriverNG.driver, Duration.ofSeconds(5L))
        .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("button")));

    if (buttons.size() == 6) {
      buttons.get(3).click();
    }
  }
}
