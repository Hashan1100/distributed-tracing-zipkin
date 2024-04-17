package org.dist.tracing;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


@org.springframework.context.annotation.Configuration
@EnableAsync
public class CustomConfiguration {

    private final BeanFactory beanFactory;

    public CustomConfiguration(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
