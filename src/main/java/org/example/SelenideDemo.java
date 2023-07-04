package org.example;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.$$;

public class SelenideDemo {

    public static void main(String[] args) {
        WebDriverRunner.setWebDriver(new ChromeDriver());
        Selenide.open("https://google.com/");
        ElementsCollection buttons = $$(By.tagName("button"));

        Selenide.closeWebDriver();
    }
}
