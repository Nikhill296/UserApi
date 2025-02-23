package com.UserApi.Entities;

import java.time.LocalDateTime;

import com.UserApi.Entities.Enums.Action;
import com.UserApi.Entities.Enums.NotificationStatus;
import com.UserApi.Entities.Enums.UserStatus;

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
@Table(name = "User_History")
@Getter
@Setter
@NoArgsConstructor
public class UserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UserStatus userStatus; // Using Enum here
    
    @Enumerated(EnumType.ORDINAL)
    private Action action; // Using Enum here
    private LocalDateTime modifiedDate;
    private NotificationStatus notifStatus; // Using Enum here
    private int quantity;
    private String remark;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private UserDetails user;
}
