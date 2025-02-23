package com.UserApi.Entities;

import java.time.LocalDateTime;

import com.UserApi.Entities.Enums.NotificationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Notification_History")
@Getter
@Setter
@NoArgsConstructor
public class NotificationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime notifSendTime;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private UserDetails user;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status; // Enum for status (Sent/Failed)
}
