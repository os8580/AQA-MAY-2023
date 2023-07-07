package org.prog.components;

import org.openqa.selenium.WebDriver;
import org.prog.util.WebDriverFactory;

public class WebDriverFacade {

    private WebDriver driver;

    public WebDriverFacade(){
        this.driver = WebDriverFactory.getDriver();
    }
}
