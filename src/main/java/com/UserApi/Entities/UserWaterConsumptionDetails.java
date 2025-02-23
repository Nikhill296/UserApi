package com.UserApi.Entities;

import java.time.LocalTime;

import org.hibernate.validator.constraints.UniqueElements;

import com.UserApi.Entities.Enums.Gender;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "User_Water_Consumption_Details")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserWaterConsumptionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double dailyGoal;
    private int cupSize;
    private int age;
    private int weight;
    private Gender gender;
    private int height;
    private LocalTime wakeupTime;
    private LocalTime bedTime;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserDetails user;
}
