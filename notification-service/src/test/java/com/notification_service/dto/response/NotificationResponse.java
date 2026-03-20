package com.notification_service.dto.response;


import com.notification_service.model.enums.NotificationType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationResponse {
    private Long id;
    private String recipient;
    private String message;
    private NotificationType type;
    private Long emergencyCaseId;
    private Boolean sent;
    private LocalDateTime createdAt;



}
