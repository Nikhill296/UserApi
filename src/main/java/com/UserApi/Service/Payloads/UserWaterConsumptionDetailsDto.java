package com.UserApi.Service.Payloads;

import java.time.LocalTime;

import com.UserApi.Entities.Enums.Gender;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserWaterConsumptionDetailsDto {


    private Long id;

    private double dailyGoal;
    @NotNull
    private int cupSize;
    private int age;
    
    @NotNull
    private int weight;
    @NotNull
    private int height;
    @NotNull
    private Gender gender;
    @NotNull
    private LocalTime wakeupTime;
    @NotNull
    private LocalTime bedTime;
    @NotNull
    private UserDetailsDto user;
}
