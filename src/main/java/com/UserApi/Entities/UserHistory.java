package com.UserApi.Entities;


import java.time.LocalDateTime;

import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.type.descriptor.jdbc.TimeAsTimestampWithTimeZoneJdbcType;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
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
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name="User_History")
public class UserHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name="user_Id")
	private Integer userId;
	private String User_status;
	private String notif_status;
	private String quentity;
	private String action;
	private String remark;
	@CurrentTimestamp
	private LocalDateTime modified_date;

}
