package com.UserApi.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.UserApi.Entities.UserHistory;
import com.UserApi.Service.Payloads.UserDetailsDto;
import com.UserApi.Service.Payloads.UserHistoryDto;

@Service
public interface UserService {
	
	UserDetailsDto createUserDto(UserDetailsDto UserDetails);
	UserDetailsDto updateUserDto(UserDetailsDto UserDetails, Integer userId);

	UserDetailsDto getUserById(Integer userId);
	List<UserDetailsDto> getAllUser();
	void deleteUser(Integer userId);
	void hardDeleteUser(Integer userId);
	List<UserHistoryDto> showUserHistory(Integer user_id);

}
