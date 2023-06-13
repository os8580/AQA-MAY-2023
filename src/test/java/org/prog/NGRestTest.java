package org.prog;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.prog.dto.SearchResultDto;
import org.testng.annotations.Test;

public class NGRestTest {

    @Test
    public void myRestTest() {
        RestAssured.useRelaxedHTTPSValidation();

        RestAssured.get("https://randomuser.me/api/?inc=gender,name,nat&noinfo&results=5")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("results.gender[0]", Matchers.anyOf(
                        Matchers.equalTo("male"),
                        Matchers.equalTo("female")
                ));

        SearchResultDto searchResultDto = RestAssured.
                get("https://randomuser.me/api/?inc=gender,name,nat&noinfo&results=5")
                .as(SearchResultDto.class);

        System.out.println(searchResultDto.getResults().size());
    }


}
