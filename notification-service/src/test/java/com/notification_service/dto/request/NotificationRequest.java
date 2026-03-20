package com.notification_service.dto.request;


import com.notification_service.model.enums.NotificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotificationRequest {


    @NotBlank(message = "Recipient required")
    private String recipient;

    @NotBlank(message = "Message required")
    private String message;

    @NotNull(message = "Type required")
    private NotificationType type;
    private Long emergencyCaseId;
}
