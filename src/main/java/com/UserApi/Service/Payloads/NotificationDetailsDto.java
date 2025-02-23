package com.UserApi.Service.Payloads;

import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationDetailsDto {

    private Long id;
    private int cupSize;
    private int order;
    private LocalTime timestampForNotifi;
    
    private Double amtOfWater; 

    private UserDetailsDto user;
}
