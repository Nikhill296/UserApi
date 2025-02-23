package com.UserApi.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UserApi.Service.UserService;
import com.UserApi.Service.Payloads.UserDetailsDto;
import com.UserApi.Service.Payloads.UserHistoryDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/UserAPI/Users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//POST - to create user
	@PostMapping("/addNewUser")
	public ResponseEntity<UserDetailsDto> createUser(@Valid @RequestBody UserDetailsDto userDto){
		
//		System.out.println("request body :: >>>>>>>> "+ userDto.toString());
		
		UserDetailsDto cereatedUserDto = userService.createUserDto(userDto);
		
		return new ResponseEntity<UserDetailsDto>(cereatedUserDto, HttpStatus.CREATED);
		
	}
	
	//PUT - to update user
	@PutMapping("/UpdateUser/{user_id}")
	public  ResponseEntity<UserDetailsDto> updateUser(@Valid @RequestBody  UserDetailsDto userDto,@PathVariable("user_id") Long user_id){
		
		UserDetailsDto cereatedUserDto = userService.updateUserDto(userDto, user_id);

		
		return new ResponseEntity<UserDetailsDto>(cereatedUserDto, HttpStatus.ACCEPTED);

	}
	
	//DELETE - to delete user
	@SuppressWarnings("unchecked")
	@DeleteMapping("/deleteUser/{user_id}")
	public ResponseEntity<?> deleteUserById(@PathVariable("user_id") Long user_id ){
		
		userService.deleteUser(user_id);
		
		return new ResponseEntity(Map.of("message","User Deleted Sucessfully"),HttpStatus.OK);
		
	}
	
	//GET - to get user
	@GetMapping("/getUsers/{user_id}")
	public ResponseEntity<UserDetailsDto> getUser(@PathVariable("user_id") Long user_id ){
		
//		System.out.println("request body :: >>>>>>>> "+ userDto.toString());
		
		UserDetailsDto cereatedUserDto = userService.getUserById(user_id);
		
		return new ResponseEntity<UserDetailsDto>(cereatedUserDto, HttpStatus.ACCEPTED);
		
	}
	
	//GET - to get All user
	@GetMapping("/getUsers")
	public ResponseEntity<List<UserDetailsDto>> getAllUser(){
		
//		System.out.println("request body :: >>>>>>>> "+ userDto.toString());
		
		List<UserDetailsDto> cereatedUserDtos = userService.getAllUser();
		
		return new ResponseEntity<List<UserDetailsDto>>(cereatedUserDtos, HttpStatus.ACCEPTED);
		
	}
	
	//GET - to get user History
		@GetMapping("/getUserHistory/{user_id}")
		public ResponseEntity<List<UserHistoryDto>> getUserHistory(@PathVariable("user_id") Long user_id){
			
//			System.out.println("request body :: >>>>>>>> "+ userDto.toString());
			
			List<UserHistoryDto> userHistDto = userService.showUserHistory(user_id);
			
			return new ResponseEntity<List<UserHistoryDto>>(userHistDto, HttpStatus.ACCEPTED);
			
		}

}
