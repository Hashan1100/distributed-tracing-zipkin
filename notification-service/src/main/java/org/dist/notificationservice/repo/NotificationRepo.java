package org.dist.notificationservice.repo;

import org.dist.notificationservice.entity.Notification;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepo extends CrudRepository<Notification, Long> {

}
