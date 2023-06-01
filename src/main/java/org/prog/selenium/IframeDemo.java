package org.prog.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class IframeDemo {

  public static void main(String[] args) {
    WebDriver driver = null;
    try{
      driver = new ChromeDriver();
      driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_iframe");
      driver.findElement(By.id("accept-choices")).click();

      System.out.println(driver.findElement(By.tagName("h1")).getText());

      WebElement iFrame = driver.findElement(By.id("iframeResult"));
      driver.switchTo().frame(iFrame);
      System.out.println(driver.findElement(By.tagName("h1")).getText());
      driver.switchTo().defaultContent();
    } finally {
      if (driver != null ){
        driver.quit();
      }
    }
  }
}
