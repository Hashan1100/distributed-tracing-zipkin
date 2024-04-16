package org.dist.tracing.services;

import brave.Span;
import brave.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;

@Service
public class EmployeeService {
    private final Tracer tracer;
    private final Executor executor;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public EmployeeService(Tracer tracer, Executor executor) {
        this.tracer = tracer;
        this.executor = executor;
    }

    public void getEmployee() throws InterruptedException {
        logger.info("Get employee service");
        Span getEmployeeService = tracer.nextSpan().name("GetEmployeeService").start();
        logger.info("Applying tracer");
        try(Tracer.SpanInScope spanInScope = tracer.withSpanInScope(getEmployeeService.start())) {
            Thread.sleep(1000L);
            logger.info("Running with a new span");
        } finally {
            getEmployeeService.finish();
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
    public void sendNotification() throws InterruptedException {
        logger.info("Sending async notification");
        Thread.sleep(1000L);
        logger.info("Sending async notification sent");
    }
}
