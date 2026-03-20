package com.notification_service.repository;

import com.notification_service.model.Notification;
import com.notification_service.model.enums.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByEmergencyCaseId(Long emergencyCaseId);
    List<Notification> findByType(NotificationType type);
    List<Notification> findBySent(Boolean sent);

}
