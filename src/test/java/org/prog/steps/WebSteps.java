package org.prog.steps;

import static org.testng.AssertJUnit.assertNotNull;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.prog.dto.NameDto;
import org.prog.pages.GooglePage;
import org.util.DataHolder;

public class WebSteps {

  public static NameDto randomUser;

  public static NameDto randomSender;
  public static NameDto randomReceiver;
  public static NameDto randomReceiver_2;
  public static NameDto randomReceiver_3;

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
  public void searchFor(String alias) {
    googlePage.setSearchValue(getRandomPersonNameLastName(alias));
    googlePage.waitForSearchSuggestions();
    googlePage.performSearch();
  }

  @Then("i see name of {string} in search hyperlinks")
  public void validateSearchResults(String alias) {
    Assertions.assertTrue(googlePage.getSearchResults().stream().anyMatch(
        we -> we.getText().contains(getRandomPersonNameLastName(alias))
    ));
  }

  private String getRandomPersonNameLastName(String alias) {
    NameDto dto = (NameDto) DataHolder.getInstance().getValue(alias);
    String searchText = "%s %s";
    assertNotNull("Random user must be assigned before search!", dto);
    return String.format(searchText, dto.getFirst(), dto.getLast());
  }
}
