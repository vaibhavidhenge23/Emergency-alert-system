package com.notification_service.service;
import com.notification_service.dto.request.NotificationRequest;
import com.notification_service.dto.response.NotificationResponse;
import com.notification_service.exception.ResourceNotFoundException;
import com.notification_service.model.Notification;
import com.notification_service.repository.NotificationRepository;
import com.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;

    @Override
    public NotificationResponse sendNotification(NotificationRequest request) {
        log.info("Sending {} notification to: {}", request.getType(), request.getRecipient());

        Notification notification = Notification.builder()
                .recipient(request.getRecipient())
                .message(request.getMessage())
                .type(request.getType())
                .emergencyCaseId(request.getEmergencyCaseId())
                .sent(true)
                .build();

        Notification saved = repository.save(notification);
        log.info("Notification sent successfully to: {}", request.getRecipient());
        return mapToResponse(saved);
    }

    @Override
    public NotificationResponse getNotificationById(Long id) {
        return mapToResponse(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found: " + id)));
    }

    @Override
    public List<NotificationResponse> getAllNotifications() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationResponse> getNotificationsByEmergencyCase(Long emergencyCaseId) {
        return repository.findByEmergencyCaseId(emergencyCaseId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteNotification(Long id) {
        Notification notification = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found: " + id));
        repository.delete(notification);
    }

    private NotificationResponse mapToResponse(Notification n) {
        return NotificationResponse.builder()
                .id(n.getId())
                .recipient(n.getRecipient())
                .message(n.getMessage())
                .type(n.getType())
                .emergencyCaseId(n.getEmergencyCaseId())
                .sent(n.getSent())
                .createdAt(n.getCreatedAt())
                .build();
    }
}
