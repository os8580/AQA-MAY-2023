package org.prog.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.enums.GoogleSelectors;

public class SampleSteps {


  @When("sample when {string}")
  public void sampleWhen(String value) {
    System.out.println("I'm searching for " + value);
  }

  @Then("sample then {string}")
  public void sampleThen(String value) {
    System.out.println("Confirming search " + value);
  }

  @Then("I use multiple params {string} and {int}")
  public void multiParamStep(String value, Integer someInt) {
    System.out.println(value + someInt);
  }


  @Given("sample step")
  public void sampleStep() {
    System.out.println("I load google page!");
  }

  @Given("sample list step:")
  public void sampleList(DataTable strings) {
    strings.asList().forEach(System.out::println);
  }

  @Given("sample map step:")
  public void sampleMap(DataTable strings) {
    strings.asMap().forEach((key, value) -> System.out.println(key + " : " + value));
  }

  @Given("sample enum step {}")
  public void sampleEnum(GoogleSelectors googleSelectors) {
    System.out.println("I will click " + googleSelectors.getLocator());
  }
}
