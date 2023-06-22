package org.enums;

import lombok.Getter;
import org.openqa.selenium.By;

public enum GoogleSelectors {
  SEARCH_INPUT(By.name("q")),
  COOKIES_BUTTONS(By.tagName("button")),
  SEARCH_RESULTS(By.tagName("a"));

  @Getter
  private final By locator;

  GoogleSelectors(By locator) {
    this.locator = locator;
  }
}
