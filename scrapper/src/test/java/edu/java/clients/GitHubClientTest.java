package edu.java.clients;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import edu.java.ScrapperApplication;
import edu.java.configuration.ClientConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

@SpringBootTest(classes = ScrapperApplication.class)
@TestPropertySource(locations = "classpath:test")
@WireMockTest
public class GitHubClientTest {
    @Autowired
    ClientConfig clientConfig;
    String url = "/repos/Molok0/java-course-2024-backend";

    @Test
    void testClient() {

        stubFor(get(urlPathMatching(url)).willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json   ")
            .withBody("<response>SUCCESS</response>")));

        clientConfig.gitHubClient().getInfo("Molok0", "java-course-2024-backend").subscribe(userRepositoryResponse -> {
            Assertions.assertEquals(userRepositoryResponse.id, 1l);
        });
    }
}
