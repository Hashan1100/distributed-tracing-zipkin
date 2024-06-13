package org.dist.notificationservice.controller;

import org.dist.notificationservice.controller.dto.NotificationDTO;
import org.dist.notificationservice.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
    private final NotificationService notificationService;

    public ApiController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/{id}")
    public NotificationDTO send(@PathVariable Long id) {
        logger.info("Received notification with id {}", id);
        NotificationDTO byId = notificationService.getById(id);
        logger.info("Received notification with id {}", id);
        return byId;
    }

    @PostMapping
    public Long saveNotification(NotificationDTO notification) {
        logger.info("Request received from to send a notification {}", notification);
        long save = notificationService.save(notification);
        logger.info("Notification saved {}", save);
        return save;
    }
}
