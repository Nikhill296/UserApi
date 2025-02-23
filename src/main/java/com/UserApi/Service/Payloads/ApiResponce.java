package com.UserApi.Service.Payloads;


public class ApiResponce<T> {
	
	T t;
	String message;
	Boolean success;
	
	
	
	public ApiResponce(T t,String message, Boolean success) {
		super();
		this.message = message;
		this.success = success;
		this.t = t;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public Boolean getSuccess() {
		return success;
	}



	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	
	

}
