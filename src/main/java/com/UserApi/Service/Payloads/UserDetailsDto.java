package com.UserApi.Service.Payloads;

import java.time.LocalDateTime;

import org.hibernate.annotations.CurrentTimestamp;

import com.UserApi.Service.Payloads.OnCreate;
import com.UserApi.Service.Payloads.OnUpdate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
	
	private int user_id ;
	
	@NotEmpty@Size(min=4,message="User must be min of 4 Character")
	private String user_name;

	@NotEmpty
	private String isd_code;

	@NotEmpty@Size(max=10,min=10, message="invalid Mobile number")
	private String mob_number;
	
	@NotEmpty@Email(message="Your Email is not Valid")
	private String email_id;
	private String gender;
	private String age;
	
	@CurrentTimestamp
	private LocalDateTime user_creation_date;
	private String active_user;	
	private LocalDateTime modified_date;

	@NotEmpty
	private String birth_date;	
	
}
