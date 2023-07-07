package org.prog.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.prog.components.WebDriverFacade;

import javax.annotation.PreDestroy;
import java.time.Duration;
import java.util.Set;

public abstract class AbstractPage {

    protected WebDriver driver;
    private final String URL;

    public AbstractPage(WebDriver driver, String URL) {
        this.driver = driver;
        this.URL = URL;
    }

    public void loadPage() {
        driver.get("about:blank");
        driver.get(URL);
    }

    public boolean isPageLoaded() {
        return new WebDriverWait(driver, Duration.ofSeconds(45L)).until(ExpectedConditions.urlToBe(URL));
    }

    public String openNewTab() {
        String initialHandle = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        return initialHandle;
    }

    public String openNewWindow() {
        String initialHandle = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        return initialHandle;
    }

    public Set<String> gethandles() {
        return driver.getWindowHandles();
    }

    public void switchToTab(String handleId) {
        driver.switchTo().window(handleId);
    }

    public void minimize(){
        driver.manage().window().minimize();
    }

    @PreDestroy
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
