package org.prog.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class RozetkaPage extends AbstractPage {

    private static final String URL = "https://rozetka.com.ua/";



    public RozetkaPage(WebDriver driver) {
        super(driver, URL);
        this.driver = driver;
    }


    public void setSearchValue(String value) {
        driver.findElement(By.name("search")).sendKeys(value);
    }

    public void performSearch() {
        driver.findElement(By.name("search")).sendKeys(Keys.ENTER);

    }

    public List<WebElement> getSearchResults() {
        List<WebElement> searchLinks = new ArrayList<>();

        List<WebElement> elements = new WebDriverWait(driver, Duration.ofSeconds(5L))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".goods-tile")));

        for (WebElement e : elements) {
            try {
                if (e.isDisplayed()) {
                    searchLinks.add(e);
                }
            } catch (StaleElementReferenceException ignored) {
                // Продолжаем выполнение цикла, если элемент стал устаревшим
            }
        }

        return searchLinks;
    }

    public String getProductName() {
        WebElement firstProduct = getSearchResults().get(0);
        WebElement productNameElement = firstProduct.findElement(By.cssSelector(".goods-tile__title"));
        return productNameElement.getText();
    }

    public String getProductPrice() {
        WebElement firstProduct = getSearchResults().get(0);
        WebElement productPriceElement = firstProduct.findElement(By.cssSelector(".goods-tile__price"));
        return productPriceElement.getText();
    }

    public void clickAddToCartButton() {
        WebElement firstProduct = getSearchResults().get(0);
        WebElement addToCartButton = firstProduct.findElement(By.cssSelector(".catalog-grid__cell:nth-child(1) .goods-tile__prices:nth-child(7) svg:nth-child(1)"));
        addToCartButton.click();
    }

    public void openCart() {
        driver.findElement(By.cssSelector(".catalog-grid__cell:nth-child(1) .goods-tile__prices:nth-child(7) svg:nth-child(1)")).click();
    }

    public void waitForTextVisibility(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '" + text + "')]")));
    }

    public String getCartProductName() {
        WebElement productNameElement = driver.findElement(By.cssSelector(".cart-product__title"));
        return productNameElement.getText();
    }

    public String getCartProductPrice() {
        WebElement productPriceElement = driver.findElement(By.cssSelector("body > app-root:nth-child(1) > rz-single-modal-window:nth-child(2) > div:nth-child(3) > div:nth-child(2) > rz-shopping-cart:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > span:nth-child(1)"));
        return productPriceElement.getText();
    }

    public void increaseProductQuantity() {
        WebElement quantityIncreaseButton = driver.findElement(By.cssSelector("button[aria-label='Добавить ещё один товар']"));
        quantityIncreaseButton.click();
    }

    public void decreaseProductQuantity() {
        WebElement quantityDecreaseButton = driver.findElement(By.cssSelector("button[aria-label='Убрать один товар']"));
        quantityDecreaseButton.click();
    }

    public int getProductQuantity() {
        WebElement quantityElement = driver.findElement(By.cssSelector("[data-testid = 'cart-counter-input']"));
        String quantityText = quantityElement.getAttribute("value");
        return Integer.parseInt(quantityText);
    }

    public int getProductPriceNumeric() {
        WebElement productPriceElement = driver.findElement(By.cssSelector("div[class='cart-receipt__sum-price']"));
        String productPriceText = productPriceElement.getText().replaceAll("\\D+", ""); // Remove non-numeric characters
        return Integer.parseInt(productPriceText);
    }
    
    




}
