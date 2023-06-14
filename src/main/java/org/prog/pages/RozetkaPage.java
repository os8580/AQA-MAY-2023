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

    public void openCart () {
        driver.findElement(By.cssSelector(".catalog-grid__cell:nth-child(1) .goods-tile__prices:nth-child(7) svg:nth-child(1)")).click();
    }
    public void waitForTextVisibility(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '" + text + "')]")));
    }









}
