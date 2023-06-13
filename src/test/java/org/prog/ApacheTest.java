package org.prog;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.prog.dto.PersonDto;
import org.prog.dto.SearchResultDto;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ApacheTest {

    @Test
    public void apacheTest() throws IOException, ParseException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ClassicHttpRequest request = new HttpGet("https://randomuser.me/api/?inc=gender,name,nat&noinfo&results=5");
        String personsJson = EntityUtils.toString(httpClient.execute(request).getEntity());

        SearchResultDto searchResultDto = mapper().readValue(personsJson, SearchResultDto.class);

        Assert.assertFalse(searchResultDto.getResults().isEmpty());
        Assert.assertTrue(containsGender(searchResultDto, "male"));
        Assert.assertTrue(containsGender(searchResultDto, "female"));
    }

    private boolean containsGender(SearchResultDto searchResultDto, String gender) {
        for (PersonDto personDto : searchResultDto.getResults()) {
            if (personDto.getGender().equals(gender)) {
                return true;
            }
        }
        return false;
    }

    private ObjectMapper mapper() {
        return new ObjectMapper();
    }
}
