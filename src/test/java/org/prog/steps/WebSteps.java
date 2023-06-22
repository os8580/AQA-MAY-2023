package org.prog.steps;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.prog.pages.GooglePage;

public class WebSteps {

  private static GooglePage googlePage;

  @BeforeAll
  public static void setupPage() {
    googlePage = new GooglePage(new ChromeDriver());
  }

  @AfterAll
  public static void tearDown() {
    googlePage.quitDriver();
  }

  @Given("I load google page")
  public void iLoadGooglePage() {
    googlePage.loadPage();
    googlePage.acceptCookiesIfPresent();
  }

  @When("i search for {string}")
  public void searchFor(String value) {
    googlePage.setSearchValue(value);
    googlePage.waitForSearchSuggestions();
    googlePage.performSearch();
  }

  @Then("i see word {string} in search hyperlinks")
  public void validateSearchResults(String value) {
    Assertions.assertTrue(googlePage.getSearchResults().stream().anyMatch(
        we -> we.getText().contains(value)
    ));
  }
}
