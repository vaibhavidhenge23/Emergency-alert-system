package com.notification_service.service;

import com.notification_service.dto.request.NotificationRequest;
import com.notification_service.dto.response.NotificationResponse;

import com.notification_service.model.enums.NotificationType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

public interface NotificationService {
    NotificationResponse sendNotification(NotificationRequest request);
    NotificationResponse getNotificationById(Long id);
    List<NotificationResponse> getAllNotifications();
    List<NotificationResponse> getNotificationsByEmergencyCase(Long emergencyCaseId);
    void deleteNotification(Long id);
}
