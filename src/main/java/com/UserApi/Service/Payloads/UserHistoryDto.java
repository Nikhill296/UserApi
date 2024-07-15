package com.UserApi.Service.Payloads;


import java.time.LocalDateTime;

import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.type.descriptor.jdbc.TimeAsTimestampWithTimeZoneJdbcType;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserHistoryDto {
	
	private Integer userId;
	private String User_status;
	private String notif_status;
	private String quentity;
	private String action;
	private String remark;
	private LocalDateTime modified_date;

}
