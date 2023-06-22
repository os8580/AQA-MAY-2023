package org.prog.steps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import java.util.stream.Collectors;
import org.prog.dto.SearchResultDto;
import org.util.DataHolder;

public class RestSteps {

  @Given("Get {int} random users from Web as {string}")
  public void getRandomUsersFromWeb(int amount, String alias) {
    String baseRequest = "https://randomuser.me/api/?inc=gender,name,nat&noinfo&results=%s";
    RestAssured.useRelaxedHTTPSValidation();

    SearchResultDto searchResultDto = RestAssured
        .get(String.format(baseRequest, amount))
        .as(SearchResultDto.class);

    DataHolder.getInstance().putValue(alias,
        searchResultDto.getResults()
            .stream()
            .map(personDto -> personDto.getName())
            .collect(Collectors.toList()));
  }
}
