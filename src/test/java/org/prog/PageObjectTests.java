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

        rozetkaPage.clickAddToCartButton();
        rozetkaPage.waitForTextVisibility("Товар добавлен в корзину");
        rozetkaPage.openCart();
        rozetkaPage.waitForTextVisibility("Вместе дешевле");
        Assert.assertTrue(rozetkaPage.isTextPresent("31 499"), "Text '31 499' not found on the page.");
        Assert.assertTrue(rozetkaPage.isTextPresent("(MLPF3HU/A)"), "Text '(MLPF3HU/A)' not found on the page.");

        Map<String, Object> productDetailsInCart = new HashMap<>();
        productDetailsInCart.put("name", rozetkaPage.getCartProductName());
        String productPriceInCart = rozetkaPage.getCartProductPrice().replaceAll("\\D+", ""); // Удаляем все нечисловые символы
        productDetailsInCart.put("price", Integer.parseInt(productPriceInCart));


        Assert.assertEquals(productDetailsInCart, productDetails, "Product details in the cart do not match the selected product details!");


        /////////////
        int initialQuantity = rozetkaPage.getProductQuantity();
        double initialPrice = rozetkaPage.getProductPriceNumeric();

// Adding one more instance of the product
        rozetkaPage.increaseProductQuantity();

        int increasedQuantity = rozetkaPage.getProductQuantity();
        double increasedPrice = rozetkaPage.getProductPriceNumeric();

        Assert.assertEquals(increasedQuantity, initialQuantity + 1, "Product quantity did not increase by 1.");
        Assert.assertTrue(increasedPrice > initialPrice, "Product price did not increase after adding another instance.");

// Removing one instance of the product
        rozetkaPage.decreaseProductQuantity();

        int decreasedQuantity = rozetkaPage.getProductQuantity();
        double decreasedPrice = rozetkaPage.getProductPriceNumeric();

        Assert.assertEquals(decreasedQuantity, initialQuantity, "Product quantity did not decrease by 1.");
        Assert.assertEquals(decreasedPrice, initialPrice, "Product price did not return to the initial value after removing an instance.");








    }

    @AfterSuite
    public void tearDown() {
        pageFactory.tearDown();
    }
}
