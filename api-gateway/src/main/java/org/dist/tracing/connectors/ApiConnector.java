package org.dist.tracing.connectors;

import org.dist.tracing.connectors.dto.NotificationRequest;
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

    public void sendRequest(String subject, String message) {
        NotificationRequest notificationRequest = new NotificationRequest(message, subject);
        String body = restClient
            .post()
            .uri("/notification")
            .body(notificationRequest)
            .retrieve()
            .body(String.class);
        logger.info("Response returned {}", body);
    }
}
