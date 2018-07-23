package edu.nikon.simpleapi.api.organization;

import edu.nikon.simpleapi.api.organization.dto.FilterOrganizationDto;
import edu.nikon.simpleapi.api.organization.dto.SaveOrganizationDto;
import edu.nikon.simpleapi.api.organization.dto.UpdateOrganizationDto;
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
public class OrganizationControllerIntTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void filterOrganizations() {
        UriComponents uc = UriComponentsBuilder.newInstance()
                .path("/api/organization/list")
                .build();

        FilterOrganizationDto filterDto = new FilterOrganizationDto();
        filterDto.setActive(true);
        filterDto.setName("нефть");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FilterOrganizationDto> req = new HttpEntity<>(filterDto, headers);

        ResponseEntity<String> res = restTemplate.postForEntity(uc.toUriString(), req, String.class);
        String json = res.getBody();

        assertEquals(HttpStatus.OK, res.getStatusCode());

        assertThat(json, isJson());
        assertThat(json, hasJsonPath("$.data[*]", hasSize(1)));
    }

    @Test
    public void getOrganizationById() {
        UriComponents uc = UriComponentsBuilder.newInstance()
                .path("/api/organization/{id}")
                .buildAndExpand(1);

        ResponseEntity<String> res = restTemplate.getForEntity(uc.toUriString(), String.class);
        String json = res.getBody();

        assertEquals(HttpStatus.OK, res.getStatusCode());

        assertThat(json, isJson());
        assertThat(json, hasJsonPath("$.data.id", equalTo(1)));
    }

    @Test
    public void saveOrganization() {
        UriComponents uc = UriComponentsBuilder.newInstance()
                .path("/api/organization/save")
                .build();

        SaveOrganizationDto saveDto = new SaveOrganizationDto();
        saveDto.setName("New organization");
        saveDto.setFullName("New organization");
        saveDto.setAddress("New organization address");
        saveDto.setPhone(null);
        saveDto.setInn("1425111136");
        saveDto.setKpp("1425GH121");
        saveDto.setActive(true);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SaveOrganizationDto> req = new HttpEntity<>(saveDto, headers);

        ResponseEntity<String> res = restTemplate.postForEntity(uc.toUriString(), req, String.class);
        String json = res.getBody();

        assertEquals(HttpStatus.OK, res.getStatusCode());

        assertThat(json, isJson());
        assertThat(json, hasJsonPath("$.data.result", equalTo("success")));
    }

    @Test
    public void updateOrganization() {
        UriComponents uc = UriComponentsBuilder.newInstance()
                .path("/api/organization/update")
                .build();

        UpdateOrganizationDto updateDto = new UpdateOrganizationDto();
        updateDto.setId(2);
        updateDto.setName("Updated organization");
        updateDto.setFullName("Updated organization");
        updateDto.setAddress("Updated organization address");
        updateDto.setPhone(null);
        updateDto.setInn("3664073127");
        updateDto.setKpp("366402002");
        updateDto.setActive(true);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UpdateOrganizationDto> req = new HttpEntity<>(updateDto, headers);

        ResponseEntity<String> res = restTemplate.postForEntity(uc.toUriString(), req, String.class);
        String json = res.getBody();

        assertEquals(HttpStatus.OK, res.getStatusCode());

        assertThat(json, isJson());
        assertThat(json, hasJsonPath("$.data.result", equalTo("success")));
    }
}