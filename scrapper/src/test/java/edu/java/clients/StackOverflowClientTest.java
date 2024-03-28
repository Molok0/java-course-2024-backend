//package edu.java.clients;
//
//import com.github.tomakehurst.wiremock.WireMockServer;
//import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
//import edu.java.ScrapperApplication;
//import edu.java.configuration.ApplicationConfig;
//import edu.java.configuration.ClientConfig;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
//import static com.github.tomakehurst.wiremock.client.WireMock.get;
//import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest(classes = ScrapperApplication.class)
//public class StackOverflowClientTest {
//    @Autowired
//    ClientConfig clientConfig;
//    @Autowired
//    ApplicationConfig applicationConfig;
//    String url = "/repos/*";
//    private static final String BODY = "{ \"id\": 752771021, \"created_at\": 2023-02-04T18:57:39Z }";
//
//    private static WireMockServer wireMockServer;
//
//    @BeforeAll
//    static void startWireMockServer() {
//        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
//        wireMockServer.start();
//        wireMockServer.stubFor(get(urlPathMatching("/repos/*"))
//            .willReturn(aResponse()
//                .withStatus(200)
//                .withHeader("Content-Type", "application/json")
//                .withBody(BODY)));
//    }
//
//    @AfterAll
//    static void stopWireMockServer() {
//        wireMockServer.stop();
//    }
//
//    @Test
//    void testWireMockServer() {
//        assertThat(wireMockServer.isRunning()).isEqualTo(true);
//    }
//
//    @Test
//    void testClient() {
//        var userRepositoryResponse =
//            clientConfig.gitHubClient(applicationConfig).getInfo("Molok0", "java-course-2024-backend")
//                .block();
//        assertThat(userRepositoryResponse.id()).isEqualTo(75277102);
//    }
//
//}
