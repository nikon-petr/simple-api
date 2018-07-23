package edu.nikon.simpleapi.api.office;

import edu.nikon.simpleapi.api.office.dto.FilterOfficeDto;
import edu.nikon.simpleapi.api.office.dto.SaveOfficeDto;
import edu.nikon.simpleapi.api.office.dto.UpdateOfficeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OfficeControllerIntTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void filterOffice() {
        UriComponents uc = UriComponentsBuilder.newInstance()
                .path("/api/office/list")
                .build();

        FilterOfficeDto filterDto = new FilterOfficeDto();
        filterDto.setOrgId(1);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FilterOfficeDto> req = new HttpEntity<>(filterDto, headers);

        ResponseEntity<String> res = restTemplate.postForEntity(uc.toUriString(), req, String.class);
        String json = res.getBody();

        assertEquals(HttpStatus.OK, res.getStatusCode());

        assertThat(json, isJson());
        assertThat(json, hasJsonPath("$.data[*]", hasSize(2)));
    }

    @Test
    public void getOfficeById() {
        UriComponents uc = UriComponentsBuilder.newInstance()
                .path("/api/office/{id}")
                .buildAndExpand(1);

        ResponseEntity<String> res = restTemplate.getForEntity(uc.toUriString(), String.class);
        String json = res.getBody();

        assertEquals(HttpStatus.OK, res.getStatusCode());

        assertThat(json, isJson());
        assertThat(json, hasJsonPath("$.data.id", equalTo(1)));
    }

    @Test
    public void saveOffice() {
        UriComponents uc = UriComponentsBuilder.newInstance()
                .path("/api/office/save")
                .build();

        SaveOfficeDto saveDto = new SaveOfficeDto();
        saveDto.setName("New office");
        saveDto.setAddress("New office address");
        saveDto.setOrganizationId(2L);
        saveDto.setPhone(null);
        saveDto.setActive(true);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SaveOfficeDto> req = new HttpEntity<>(saveDto, headers);

        ResponseEntity<String> res = restTemplate.postForEntity(uc.toUriString(), req, String.class);
        String json = res.getBody();

        assertEquals(HttpStatus.OK, res.getStatusCode());

        assertThat(json, isJson());
        assertThat(json, hasJsonPath("$.data.result", equalTo("success")));
    }

    @Test
    public void updateOffice() {
        UriComponents uc = UriComponentsBuilder.newInstance()
                .path("/api/office/update")
                .build();

        UpdateOfficeDto updateDto = new UpdateOfficeDto();
        updateDto.setId(4);
        updateDto.setName("Updated office");
        updateDto.setAddress("Updated office address");
        updateDto.setOrganizationId(2L);
        updateDto.setPhone(null);
        updateDto.setActive(true);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UpdateOfficeDto> req = new HttpEntity<>(updateDto, headers);

        ResponseEntity<String> res = restTemplate.postForEntity(uc.toUriString(), req, String.class);
        String json = res.getBody();

        assertEquals(HttpStatus.OK, res.getStatusCode());

        assertThat(json, isJson());
        assertThat(json, hasJsonPath("$.data.result", equalTo("success")));
    }
}