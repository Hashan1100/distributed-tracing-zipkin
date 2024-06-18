package org.dist.tracing;

import io.micrometer.context.ContextSnapshotFactory;
import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;

import java.util.concurrent.Executor;

@Configuration
public class ExecutorTraceContextConfig {

    @Bean
    public TaskDecorator otelTaskDecorator() {
        return runnable -> ContextSnapshotFactory
            .builder()
            .build()
            .captureAll()
            .wrap(runnable);
    }

    @Bean("asyncExecutorPool1")
    public Executor asyncExecutorPool1(TaskDecorator otelTaskDecorator) {
        return new ThreadPoolTaskExecutorBuilder()
            .corePoolSize(5)
            .maxPoolSize(10)
            .queueCapacity(10)
            .threadNamePrefix("threadPoolExecutor1-")
            .taskDecorator(otelTaskDecorator)
            .build();
    }
}
