package com.UserApi.Service.Payloads;

import java.time.LocalDateTime;

import com.UserApi.Entities.Enums.NotificationStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class NotificationHistoryDto {

    
    private Long id;

    private LocalDateTime notifSendTime;
    
    private UserDetailsDto user;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status; // Enum for status (Sent/Failed)
}
