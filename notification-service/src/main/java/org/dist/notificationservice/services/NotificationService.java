package org.dist.notificationservice.services;

import org.dist.notificationservice.controller.dto.NotificationDTO;
import org.dist.notificationservice.entity.Notification;
import org.dist.notificationservice.repo.NotificationRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationRepo notificationRepo;
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public NotificationService(NotificationRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }

    public long save(NotificationDTO notificationDTO) {
        logger.info("Saving notification {}", notificationDTO);
        Notification notification = new Notification();
        notification.setMessage(notificationDTO.message());
        notification.setSubject(notificationDTO.subject());
        Notification save = notificationRepo.save(notification);
        return save.getId();
    }

    public NotificationDTO getById(long id) {
        logger.info("Retrieving notification by id {}", id);
        Notification notification = notificationRepo.findById(id).orElse(null);
        if (notification == null) {
            return null;
        }
        return new NotificationDTO(notification.getMessage(), notification.getSubject());
    }
}
