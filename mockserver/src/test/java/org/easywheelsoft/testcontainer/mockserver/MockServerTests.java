package org.easywheelsoft.testcontainer.mockserver;

import org.junit.Rule;
import org.junit.Test;
import org.mockserver.client.MockServerClient;
import org.springframework.web.reactive.function.client.WebClient;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.utility.DockerImageName;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertTrue;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * @author echooymxq
 **/
public class MockServerTests {

    @Rule
    public MockServerContainer mockServer = new MockServerContainer(DockerImageName
            .parse("mockserver/mockserver:5.13.0"));

    @Test
    public void testMockServer() {
        MockServerClient mockServerClient = new MockServerClient(mockServer.getHost(), mockServer.getServerPort());
        assertTrue("Mockserver running", mockServerClient.hasStarted());
        mockServerClient.when(request().withPath("/hello"))
                .respond(response().withBody("foo"));

        String endpoint = mockServer.getEndpoint();
        WebClient client = WebClient.builder()
                .baseUrl(endpoint)
                .build();
        Mono<String> result = client.get()
                .uri("/hello")
                .retrieve()
                .bodyToMono(String.class);
        StepVerifier.create(result)
                .expectNextMatches("foo"::equals)
                .verifyComplete();
    }

}
