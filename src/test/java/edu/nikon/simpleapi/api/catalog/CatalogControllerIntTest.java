package edu.nikon.simpleapi.api.catalog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CatalogControllerIntTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getDocs() {
        UriComponents uc = UriComponentsBuilder.newInstance()
                .path("/api/docs")
                .build();

        ResponseEntity<String> res = restTemplate.getForEntity(uc.toUriString(), String.class);
        String json = res.getBody();

        assertEquals(HttpStatus.OK, res.getStatusCode());

        assertThat(json, isJson());
        assertThat(json, hasJsonPath("$.data[*]", hasSize(13)));
    }

    @Test
    public void getCountries() {
        UriComponents uc = UriComponentsBuilder.newInstance()
                .path("/api/countries")
                .build();

        ResponseEntity<String> res = restTemplate.getForEntity(uc.toUriString(), String.class);
        String json = res.getBody();

        assertEquals(HttpStatus.OK, res.getStatusCode());

        assertThat(json, isJson());
        assertThat(json, hasJsonPath("$.data[*]", hasSize(241)));
    }
}