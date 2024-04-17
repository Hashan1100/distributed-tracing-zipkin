package org.dist.notificationservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/")
    public String send() {
        logger.info("Request received from to send a notification");
        logger.info("Notification sent");
        return "Notification sent";
    }
}
