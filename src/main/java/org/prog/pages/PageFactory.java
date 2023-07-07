package org.prog.pages;

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;
import org.prog.util.WebDriverFactory;

@Component
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
