package com.UserApi.Controller;

import java.util.List;
import java.util.Map;

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
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/UserAPI/Users")
public class UserController {
	
	private final UserService userService;
	
	//POST - to create user
	@PostMapping("/addNewUser")
	public ResponseEntity<UserDetailsDto> createUser(@Valid @RequestBody UserDetailsDto userDto){
		
//		log.info("request body :: >>>>>>>> "+ userDto.toString());
		
		UserDetailsDto cereatedUserDto = userService.createUserDto(userDto);
		
		return new ResponseEntity<UserDetailsDto>(cereatedUserDto, HttpStatus.CREATED);
		
	}
	
	//PUT - to update user
	@PutMapping("/UpdateUser/{userId}")
	public  ResponseEntity<UserDetailsDto> updateUser(@Valid @RequestBody  UserDetailsDto userDto,@PathVariable("userId") Long userId){
		
		UserDetailsDto cereatedUserDto = userService.updateUserDto(userDto, userId);

		
		return new ResponseEntity<UserDetailsDto>(cereatedUserDto, HttpStatus.ACCEPTED);

	}
	
	//DELETE - to delete user
	@SuppressWarnings("unchecked")
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<?> deleteUserById(@PathVariable("userId") Long userId ){
		
		userService.deleteUser(userId);
		
		return new ResponseEntity(Map.of("message","User Deleted Sucessfully"),HttpStatus.OK);
		
	}
	
	//GET - to get user
	@GetMapping("/getUsers/{userId}")
	public ResponseEntity<UserDetailsDto> getUser(@PathVariable("userId") Long userId ){
		
//		log.info("request body :: >>>>>>>> "+ userDto.toString());
		
		UserDetailsDto cereatedUserDto = userService.getUserById(userId);
		
		return new ResponseEntity<UserDetailsDto>(cereatedUserDto, HttpStatus.ACCEPTED);
		
	}
	
	//GET - to get All user
	@GetMapping("/getUsers")
	public ResponseEntity<List<UserDetailsDto>> getAllUser(){
		
//		log.info("request body :: >>>>>>>> "+ userDto.toString());
		
		List<UserDetailsDto> cereatedUserDtos = userService.getAllUser();
		
		return new ResponseEntity<List<UserDetailsDto>>(cereatedUserDtos, HttpStatus.ACCEPTED);
		
	}
	
	//GET - to get user History
		@GetMapping("/getUserHistory/{userId}")
		public ResponseEntity<List<UserHistoryDto>> getUserHistory(@PathVariable("userId") Long userId){
			
//			log.info("request body :: >>>>>>>> "+ userDto.toString());
			
			List<UserHistoryDto> userHistDto = userService.showUserHistory(userId);
			
			return new ResponseEntity<List<UserHistoryDto>>(userHistDto, HttpStatus.ACCEPTED);
			
		}

}
