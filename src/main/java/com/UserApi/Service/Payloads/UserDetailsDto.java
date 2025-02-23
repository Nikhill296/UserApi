package com.UserApi.Service.Payloads;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.UserApi.Entities.NotificationDetails;
import com.UserApi.Entities.UserWaterConsumptionDetails;
import com.UserApi.Entities.Enums.Gender;
import com.UserApi.Service.CustomAnno.isPrimeNum;
import com.UserApi.Service.CustomAnno.properPassword;
import com.UserApi.Service.Payloads.OnCreate;
import com.UserApi.Service.Payloads.OnUpdate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDetailsDto {
	
	private Long userId ;
	
	@NotEmpty@Size(min=4,message="User must be min of 4 Character")
	private String userName;

//	@NotEmpty
//	@isPrimeNum // custom annotation
	
	private String isdCode;

	@NotEmpty@Size(max=10,min=10)	
	private String mobNumber;
	
	@NotEmpty@Email(message="Your Email is not Valid")
//	@properPassword
	private String emailId;
	private Gender gender;	
	@CurrentTimestamp
	private LocalDateTime userCreationDate;
	private boolean active_user;	
	private LocalDateTime modifiedDate;

//	@NotEmpty@Past(message = "Birth date must be in the past")
	private LocalDate birthDate;
	
//    private UserWaterConsumptionDetailsDto userWaterConsumptionDetails;
//    private  List<NotificationDetailsDto> user = new ArrayList<>();
	
}
