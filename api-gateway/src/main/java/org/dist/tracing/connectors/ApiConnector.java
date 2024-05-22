package org.dist.tracing.connectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ApiConnector {
    private static final Logger logger = LoggerFactory.getLogger(ApiConnector.class);
    private final RestClient restClient;

    public ApiConnector(RestClient restClient) {
        this.restClient = restClient;
    }

    public void sendRequest() {
        String body = restClient
            .get()
            .uri("/notification/")
            .retrieve()
            .body(String.class);
        logger.info("Response returned {}", body);
    }
}
