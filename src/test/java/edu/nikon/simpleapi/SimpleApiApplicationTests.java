package edu.nikon.simpleapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SimpleApiApplicationTests {

    @LocalServerPort
    private int port;

    @Test
    public void contextLoads() {
    }

    @TestConfiguration
    class Config {

        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            UriComponents uc = UriComponentsBuilder.newInstance()
                    .scheme("http")
                    .host("localhost")
                    .port(port)
                    .build();

            return new RestTemplateBuilder()
                    .rootUri(uc.toUriString());
        }
    }
}
