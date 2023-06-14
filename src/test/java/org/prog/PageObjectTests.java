package org.prog;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.prog.pages.GooglePage;
import org.prog.pages.PageFactory;
import org.prog.pages.Pages;
import org.prog.pages.RozetkaPage;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageObjectTests {

    private PageFactory pageFactory;

    @BeforeSuite
    public void setupDriver() {
        pageFactory = new PageFactory(new ChromeDriver());
    }

    @Test
    public void testGoogleUsingPageObjectWithChrome() {
        GooglePage googlePage = (GooglePage) pageFactory.get(Pages.GOOGLE);
        RozetkaPage rozetkaPage = (RozetkaPage) pageFactory.get(Pages.ROZETKA);
        googlePage.loadPage();
        googlePage.acceptCookiesIfPresent();
        googlePage.setSearchValue("rozetka ua");
        googlePage.performSearch();
        List<WebElement> searchResults = googlePage.getSearchResults();
        Assert.assertTrue(searchResults.size() > 1, "No search results found!");
        searchResults.get(0).click();
        Assert.assertTrue(rozetkaPage.isPageLoaded());
        rozetkaPage.setSearchValue("Iphone");
        rozetkaPage.performSearch();

        List<WebElement> searchResultsRozetka = rozetkaPage.getSearchResults();
        Assert.assertFalse(searchResultsRozetka.isEmpty(), "No search results found on Rozetka page!");


        Map<String, Object> productDetails = new HashMap<>();
        productDetails.put("name", rozetkaPage.getProductName());
        String productPrice = rozetkaPage.getProductPrice().replaceAll("\\D", ""); // Удаляем все нечисловые символы
        productDetails.put("price", Integer.parseInt(productPrice));



    }

    @AfterSuite
    public void tearDown() {
        pageFactory.tearDown();
    }
}
