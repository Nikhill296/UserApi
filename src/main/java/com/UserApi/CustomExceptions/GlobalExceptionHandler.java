package com.UserApi.CustomExceptions;

import java.util.HashMap;

import org.hibernate.mapping.Map;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.UserApi.Service.Payloads.ApiResponce;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce> resourceNotFoundExceptionHandler(ResourceNotFoundException Ex){
		
		String msg = Ex.getMessage();
		
		
		ApiResponce res = new ApiResponce(msg, false);
		
		
		return new ResponseEntity<ApiResponce>(res, HttpStatus.NOT_FOUND);
		
		
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<HashMap<String, String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException Ex){
		
		HashMap<String, String> rsp = new HashMap<>();
		
		Ex.getBindingResult().getAllErrors().forEach((error) -> {

			String field = ((FieldError) error).getField();
			String msg = error.getDefaultMessage();
			rsp.put(field, msg);
			
		});
		
		return new ResponseEntity<HashMap<String,String>>(rsp,HttpStatus.BAD_REQUEST);
		
		
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<HashMap<String,String>> DataIntegrityViolationExceptionHandler (DataIntegrityViolationException Ex) {
		
		HashMap<String, String> rsp = new HashMap<>();
		
	    String msg = "Mobile Number And Email Shuold be unique, User is already Present with same Mobile Number or Email or Both";
	    rsp.put("msg", msg);
	   
	    return new ResponseEntity<HashMap<String,String>>(rsp,HttpStatus.BAD_REQUEST);
		
	
	}

}
