package org.prog.steps;

import io.cucumber.java.en.Given;
import lombok.SneakyThrows;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.util.WebDriverFactory;

import java.util.Random;

public class SampleSteps {
    private long sleepDuration;

    private static final ThreadLocal<WebDriver> webDriverLocal = ThreadLocal.withInitial(WebDriverFactory::getDriver);

    @SneakyThrows
    @Given("wait for random amount of time")
    public void waitForRandomAmountOfTime() {
        Random random = new Random();
        sleepDuration = 2000 + random.nextInt(5) * 1000;
        System.out.println("Thread " + Thread.currentThread().getId() + " pause for " + sleepDuration + " millis");
        System.out.println("[T] Thread " + Thread.currentThread().getId() + " : " + System.currentTimeMillis());
        Thread.sleep(sleepDuration);
        System.out.println("[T] Thread " + Thread.currentThread().getId() + " : " + System.currentTimeMillis());
    }

    @SneakyThrows
    @Given("random browser stuff {string}")
    public void randomBrowserStuff(String url) {
        webDriverLocal.get().get(url);
        Random random = new Random();
        sleepDuration = 10000 + random.nextInt(20) * 1000;
        Thread.sleep(sleepDuration);
        webDriverLocal.get().get("https://google.com/");
        webDriverLocal.get().quit();
    }
}
