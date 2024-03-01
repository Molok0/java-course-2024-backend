//package edu.java.clients;
//
//import com.github.tomakehurst.wiremock.junit5.WireMockTest;
//import edu.java.ScrapperApplication;
//import edu.java.configuration.ApplicationConfig;
//import edu.java.configuration.ClientConfig;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
//import static com.github.tomakehurst.wiremock.client.WireMock.get;
//import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
//import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest(classes = ScrapperApplication.class)
//@TestPropertySource(locations = "classpath:test")
//@WireMockTest(httpPort = 8080)
//public class GitHubClientTest {
//    @Autowired
//    ClientConfig clientConfig;
//    @Autowired
//    ApplicationConfig applicationConfig;
//    String url = "/repos/*";
//    private static final String BODY = "{ \"id\": 752771021, \"created_at\": 2023-02-04T18:57:39Z }";
//
//    @Test
//    void testClient() {
//        System.out.println(applicationConfig.urlClient().githubDefaultUrl());
//
//        stubFor(get(urlPathMatching(url))
//            .willReturn(aResponse()
//                .withStatus(200)
//                .withHeader("Content-Type", "application/json")
//                .withBody(BODY)));
//
//        var userRepositoryResponse =
//            clientConfig.gitHubClient(applicationConfig).getInfo("Molok0", "java-course-2024-backend")
//                .block();
//        assertThat(userRepositoryResponse.id()).isEqualTo(75277102);
//    }
//}
