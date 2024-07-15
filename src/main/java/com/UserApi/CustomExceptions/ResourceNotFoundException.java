package com.UserApi.CustomExceptions;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
	
	String ResourceName;
	String FieldName;
	long fielVal;
	
	public ResourceNotFoundException(String resourceName, String fieldName, long fielVal) {
		super(String.format("%s Not Found with %s : %s ", resourceName, fieldName, fielVal));
		ResourceName = resourceName;
		FieldName = fieldName;
		this.fielVal = fielVal;
	}
	

	
}
