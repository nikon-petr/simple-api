package edu.nikon.simpleapi.api.user;

import edu.nikon.simpleapi.api.user.dto.FilterUserDto;
import edu.nikon.simpleapi.api.user.dto.SaveUserDto;
import edu.nikon.simpleapi.api.user.dto.UpdateUserDto;
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

import java.util.Calendar;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void filterUser() {
        UriComponents uc = UriComponentsBuilder.newInstance()
                .path("/api/user/list")
                .build();

        FilterUserDto filterDto = new FilterUserDto();
        filterDto.setOfficeId(1L);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FilterUserDto> req = new HttpEntity<>(filterDto, headers);

        ResponseEntity<String> res = restTemplate.postForEntity(uc.toUriString(), req, String.class);
        String json = res.getBody();

        assertEquals(HttpStatus.OK, res.getStatusCode());

        assertThat(json, isJson());
        assertThat(json, hasJsonPath("$.data[*]", hasSize(2)));
    }

    @Test
    public void getUserById() {
        UriComponents uc = UriComponentsBuilder.newInstance()
                .path("/api/user/{id}")
                .buildAndExpand(1);

        ResponseEntity<String> res = restTemplate.getForEntity(uc.toUriString(), String.class);
        String json = res.getBody();

        assertEquals(HttpStatus.OK, res.getStatusCode());

        assertThat(json, isJson());
        assertThat(json, hasJsonPath("$.data.id", equalTo(1)));
    }

    @Test
    public void saveUser() {
        UriComponents uc = UriComponentsBuilder.newInstance()
                .path("/api/user/save")
                .build();

        Calendar docDate = Calendar.getInstance();
        docDate.set(2012, Calendar.NOVEMBER, 14);

        SaveUserDto saveDto = new SaveUserDto();
        saveDto.setOfficeId(2L);
        saveDto.setFirstName("New user first name");
        saveDto.setPosition("New user position");
        saveDto.setCitizenshipCode("643");
        saveDto.setDocCode("21");
        saveDto.setDocNumber("6310221135");
        saveDto.setDocDate(docDate.getTime());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SaveUserDto> req = new HttpEntity<>(saveDto, headers);

        ResponseEntity<String> res = restTemplate.postForEntity(uc.toUriString(), req, String.class);
        String json = res.getBody();

        assertEquals(HttpStatus.OK, res.getStatusCode());

        assertThat(json, isJson());
        assertThat(json, hasJsonPath("$.data.result", equalTo("success")));
    }

    @Test
    public void updateUser() {
        UriComponents uc = UriComponentsBuilder.newInstance()
                .path("/api/user/update")
                .build();

        Calendar docDate = Calendar.getInstance();
        docDate.set(2012, Calendar.NOVEMBER, 14);

        UpdateUserDto saveDto = new UpdateUserDto();
        saveDto.setId(4L);
        saveDto.setOfficeId(2L);
        saveDto.setFirstName("Updated user first name");
        saveDto.setPosition("Updated user position");
        saveDto.setCitizenshipCode("643");
        saveDto.setDocCode("21");
        saveDto.setDocNumber("6310221145");
        saveDto.setDocDate(docDate.getTime());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UpdateUserDto> req = new HttpEntity<>(saveDto, headers);

        ResponseEntity<String> res = restTemplate.postForEntity(uc.toUriString(), req, String.class);
        String json = res.getBody();

        assertEquals(HttpStatus.OK, res.getStatusCode());

        assertThat(json, isJson());
        assertThat(json, hasJsonPath("$.data.result", equalTo("success")));
    }
}