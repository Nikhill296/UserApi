package com.UserApi.Entities;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.UserApi.Entities.Enums.NotificationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;

@Entity
@Table(name = "Notification_Details")
@Getter
@Setter
@NoArgsConstructor
public class NotificationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int notifOrder;

    private int cupSize;
    private LocalTime timestampForNotifi;
    
    private Double amtOfWater;
   
    @Enumerated(EnumType.STRING)
    private NotificationStatus status; // Enum for status (Sent/Failed)
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private UserDetails user;
}
