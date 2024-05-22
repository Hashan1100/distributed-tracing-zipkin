package org.dist.tracing;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class CustomConfiguration {

    private final BeanFactory beanFactory;

    public CustomConfiguration(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Bean("notificationApiClient")
    RestClient notificationApiClient(@Value("${notification.service.url}") String restApi2Url, RestClient.Builder restClientBuilder) {
        return restClientBuilder.baseUrl(restApi2Url)
            .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE).build();
    }
}
