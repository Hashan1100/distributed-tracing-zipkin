package org.dist.tracing.services;


import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.dist.tracing.connectors.ApiConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;

@Service
public class EmployeeService {
    private final Tracer tracer;
    private final Executor executor;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private final ApiConnector apiConnector;
    public EmployeeService(Tracer tracer, @Qualifier("asyncExecutorPool1") Executor executor, ApiConnector apiConnector) {
        this.tracer = tracer;
        this.executor = executor;
        this.apiConnector = apiConnector;
    }

    public void getEmployee() throws InterruptedException {
        logger.info("Get employee service");
        Span getEmployeeService = tracer.nextSpan().name("GetEmployeeService").start();
        logger.info("Applying tracer");
        try(Tracer.SpanInScope spanInScope = tracer.withSpan(getEmployeeService.start())) {
            Thread.sleep(1000L);
            logger.info("Running with a new span");
        } finally {
            getEmployeeService.end();
        }
        logger.info("Running with older span");
    }

    public void getMultipleEmployees() {
        logger.info("Running in multiple thread");
        Runnable runnable = () -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                logger.info("Error occurred while trying to run", e);
            }
            logger.info("I'm inside the new thread - with a new span");
        };
        logger.info("Original thread");
        executor.execute(runnable);
    }


    @Async
    public void sendNotification() {
        logger.info("Sending async notification");
        apiConnector.sendRequest("Subject 1", "Message 1");
        logger.info("Sending async notification sent");
    }
}
