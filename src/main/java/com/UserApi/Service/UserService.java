package com.UserApi.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.UserApi.Entities.UserHistory;
import com.UserApi.Service.Payloads.UserDetailsDto;
import com.UserApi.Service.Payloads.UserHistoryDto;

@Service
public interface UserService {
	
	UserDetailsDto createUserDto(UserDetailsDto UserDetails);
	UserDetailsDto updateUserDto(UserDetailsDto UserDetails, Long userId);

	UserDetailsDto getUserById(Long userId);
	List<UserDetailsDto> getAllUser();
	void deleteUser(Long userId);
	void hardDeleteUser(Long userId);
	List<UserHistoryDto> showUserHistory(Long user_id);

}
