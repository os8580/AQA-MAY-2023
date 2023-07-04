package org.prog.steps;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.prog.dto.NameDto;
import org.prog.pages.GooglePage;
import org.prog.pages.PageFactory;
import org.prog.pages.Pages;
import org.util.DataHolder;

import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;

import static org.testng.AssertJUnit.assertNotNull;

public class WebSteps {

    public static NameDto randomUser;

    public static NameDto randomSender;
    public static NameDto randomReceiver;
    public static NameDto randomReceiver_2;
    public static NameDto randomReceiver_3;

    private static GooglePage googlePage;

    @BeforeAll
    public static void setupPage() {
        googlePage = (GooglePage) PageFactory.get(Pages.GOOGLE);
    }

    @AfterAll
    public static void tearDown() {
        googlePage.quitDriver();
    }

    @SneakyThrows
    @Given("A new tab is opened")
    public void openNewTab() {
        if (true) {
            System.out.println("aaaa");
        }
        String initialHandle = googlePage.openNewTab();
        String newTab = googlePage.openNewTab();
        String newWindow = googlePage.openNewWindow();
        googlePage.gethandles().forEach(System.out::println);
        googlePage.switchToTab(initialHandle);
        googlePage.minimize();
        Thread.sleep(1000);
    }

    @Given("I load google page")
    public void iLoadGooglePage() {
        googlePage.loadPage();
        googlePage.acceptCookiesIfPresent();
    }

    @When("i search for {string}")
    public void searchFor(String alias) throws InterruptedException {
        googlePage.setSearchValue(getRandomPersonNameLastName(alias));
        Thread.sleep(250);
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

    private static ChromeOptions options() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("enableVideo", true);
            put("enableVNC", true);
        }});
        return chromeOptions;
    }

    private static URL getSelenoidUrl() throws UnknownHostException, MalformedURLException {
        if (Inet4Address.getLocalHost().getHostAddress().startsWith("172")) {
            return new URL("http://selenoid-selenoid-1:4444/wd/hub");
        } else {
            return new URL("http://localhost:4444/wd/hub");
        }
    }
}
