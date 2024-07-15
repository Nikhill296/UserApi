package com.UserApi.Entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="User_Details")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int user_id ;
	
	private String user_name;
	private String isd_code;
	private String mob_number;
	private String email_id;
	private String gender;
	private String age;
	private LocalDateTime user_creation_date;
	private String active_user;
	
	@CurrentTimestamp
	private LocalDateTime modified_date;
	
	private String birth_date;	

//	private void updateModifiedDate() {
//	    this.modified_date = LocalDateTime.now();
//	}
//	
	
}
