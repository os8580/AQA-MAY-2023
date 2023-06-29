package org.util;

import lombok.SneakyThrows;
import org.enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class WebDriverFactory {

    @SneakyThrows
    public static WebDriver getDriver() {
        String type = System.getProperty("driver.type", "CHROME_DOCKER");
        BrowserType browserType = BrowserType.valueOf(type);
        switch (browserType) {
            case CHROME:
                return initLocalDriver();
            case CHROME_REMOTE:
                return initRemoteDriver();
            default:
                return initDockerDriver();
        }
    }

    private static WebDriver initLocalDriver() {
        return new ChromeDriver();
    }

    private static WebDriver initRemoteDriver() throws MalformedURLException {
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options());
    }

    private static WebDriver initDockerDriver() throws MalformedURLException {
        return new RemoteWebDriver(new URL("http://selenoid-selenoid-1:4444/wd/hub"), options());
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
}
