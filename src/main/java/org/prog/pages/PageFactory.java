package org.prog.pages;

import org.enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.util.WebDriverFactory;

public class PageFactory {

    private static final WebDriver driver = WebDriverFactory.getDriver();

    public static AbstractPage get(Pages pages) {
        switch (pages) {
            case GOOGLE:
                return new GooglePage(driver);
            case ROZETKA:
                return new RozetkaPage(driver);
            default:
                throw new RuntimeException();
        }
    }

    public void tearDown() {
        driver.quit();
    }
}
