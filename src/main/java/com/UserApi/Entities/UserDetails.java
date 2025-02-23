package com.UserApi.Entities;


import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CurrentTimestamp;

import com.UserApi.Entities.Enums.Gender;
import com.UserApi.Entities.Enums.UserStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;
@Entity
@Table(name = "User_Details")
@Getter
@Setter
@NoArgsConstructor
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String userName;
    private String isdCode;
    private String mobnumber;
    private String emailId;

    @Enumerated(EnumType.STRING)
    private Gender gender; // Enum for gender

    private LocalDateTime userCreationDate;
    private boolean activeUser;
    
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus; // Enum for user status (Active, Inactive)

    @CurrentTimestamp
    private LocalDateTime modifiedDate;

    private LocalDate birthDate;
    
    @OneToOne(mappedBy = "user")
    private UserWaterConsumptionDetails userWaterConsumptionDetails;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private  List<NotificationDetails> notificationDetails = new ArrayList<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private  List<NotificationHistory> notificationHistory = new ArrayList<>();

}
