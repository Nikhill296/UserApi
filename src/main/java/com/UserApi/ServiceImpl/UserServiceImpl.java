package com.UserApi.ServiceImpl;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UserApi.CustomExceptions.ResourceNotFoundException;
import com.UserApi.Entities.UserDetails;
import com.UserApi.Entities.UserHistory;
import com.UserApi.Entities.Enums.Action;
import com.UserApi.Entities.Enums.UserStatus;
import com.UserApi.Repository.HistoryRepo;
import com.UserApi.Repository.UserRepo;
import com.UserApi.Service.UserService;
import com.UserApi.Service.Payloads.UserDetailsDto;
import com.UserApi.Service.Payloads.UserHistoryDto;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepo userRepo;
	private final ModelMapper modelMapper;
	private final HistoryRepo histRepo;

	@Override
	public UserDetailsDto createUserDto(UserDetailsDto UserDto) {
		
		
//		log.info("userdto in request >>>>>>> "+UserDto.toString());
		UserDetails user = this.getUserDetailsObject(UserDto);
		user.setUserCreationDate(LocalDateTime.now());
		
		UserDetails savedUser = this.userRepo.save(user);

//		UserHistoryDto userHisDto = new UserHistoryDto();
		
//		userHisDto.setUserId(savedUser.getUserId());
//		userHisDto.setUser_status("UC");
//		userHisDto.setRemark("User Created Successfully");

//		UserHistory userHis = this.gethistorObject(userHisDto);
		
//		histRepo.save(userHis);
		
	    // Save the user in the repository
//	    UserDetails savedUser = this.userRepo.save(user);

	    // Prepare UserHistory DTO
	    UserHistory userHist = new UserHistory();
	    userHist.setUser(savedUser);
	    userHist.setUserStatus(UserStatus.ACTIVE);  // Setting Enum 'ACTIVE'
	    userHist.setAction(Action.CREATED);  // Setting Enum 'CREATED'
	    userHist.setRemark("User Created Successfully");

	    // Convert DTO to entity
//	    UserHistory userHis = this.gethistorObject(userHisDto);

	    // Save the user history record
	    histRepo.save(userHist);
		
		return  this.getUserDtoObject(savedUser);
	}



	@Override
	public UserDetailsDto updateUserDto(UserDetailsDto userDtoReq, Long userId) {

	    // Retrieve the user to update
	    UserDetails user = userRepo.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("user_Details", "User", userId));

	    // Update user data based on the request DTO
	    user = this.updateUserDataIfInReq(userDtoReq, user);

	    // Save the updated user
	    UserDetails updatedUser = userRepo.save(user);

	    // Log the update action in UserHistory
	    UserHistory userHis = new UserHistory();
	    userHis.setUserStatus(UserStatus.ACTIVE); // Using Enum for user status (ACTIVE)
	    userHis.setAction(Action.UPDATED); // Using Enum for action (UPDATED)
	    userHis.setRemark("User updated Successfully");
	    userHis.setUser(user); // Setting user relation for history

	    // Save the user history
	    histRepo.save(userHis);

	    log.info("####### user details updated successfully #######");

	    return this.getUserDtoObject(updatedUser);
	}


	private UserDetails updateUserDataIfInReq(UserDetailsDto userDto, UserDetails user) {

		try {
			
			user.setUserId(userDto.getUserId());

			if( userDto.getIsdCode() != null && !userDto.getIsdCode().isEmpty() )
			user.setIsdCode(userDto.getIsdCode());

			if( userDto.getMobNumber() != null && !userDto.getMobNumber().isEmpty())
			user.setMobnumber(userDto.getMobNumber());

			if( userDto.getEmailId() != null && !userDto.getEmailId().isEmpty() )
			user.setEmailId(userDto.getEmailId());

			if( userDto.getGender() != null )
			user.setGender(userDto.getGender());

//			if( userDto.getAge() != null && !userDto.getAge().isEmpty() )
//			user.setAge(userDto.getAge());

			if( userDto.getBirthDate() != null)
			user.setBirthDate(userDto.getBirthDate());
			
			
			
		} catch (Exception e) {
			log.info("Exception in updateUserDataIfInReq  " + e.getMessage());
		}
		return user;

	}

	@Override
	public UserDetailsDto getUserById(Long userId) {

		UserDetails user = new UserDetails();
		
			user = this.userRepo.findById(userId)
					  .orElseThrow(()-> new ResourceNotFoundException("user_Details","User",userId));
			boolean ispresent = this.userRepo.existsById(userId);
			
		return this.getUserDtoObject(user);
	}

	@Override
	public List<UserDetailsDto> getAllUser() {

		List<UserDetails> userList = new ArrayList<>();
		List<UserDetailsDto> userDtoList = new ArrayList<>();
		
		try {
			userList = (ArrayList<UserDetails>) userRepo.findAll();
			
//			my code
//			for (UserDetails user : userList) 
//				userDtoList.add(this.getUserDtoObject(user));
				
//			Durgesh code
			userDtoList = userList.stream().map(user -> this.getUserDtoObject(user)).collect(Collectors.toList());
			
			log.info("####### All users Featched successfully #######");

		}catch (Exception e) {
			
			log.info("Exception in getting All user " + e.getMessage());

		}
		
		return userDtoList;
	}

	@Override
	public void deleteUser(Long userId) {

	    // Retrieve the user to delete
	    UserDetails user = userRepo.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("user_Details", "User", userId));

	    // Mark the user as inactive (soft delete)
	    user.setActiveUser(false); // Set the user status to inactive
	    UserDetails updatedUser = userRepo.save(user);

	    // Log the delete action in UserHistory
	    UserHistory userHis = new UserHistory();
	    userHis.setUserStatus(UserStatus.INACTIVE); // Using Enum for user status (INACTIVE)
	    userHis.setAction(Action.DELETED); // Using Enum for action (DELETED)
	    userHis.setRemark("User deleted Successfully (soft delete)");
	    userHis.setUser(user); // Setting user relation for history

	    // Save the user history
	    histRepo.save(userHis);

	    log.info("####### user deleted successfully (soft delete) #######");
	}

	
	@Override
	public void hardDeleteUser(Long userId) {

	    // Retrieve the user to hard delete
	    UserDetails user = userRepo.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("user_Details", "User", userId));

	    // Delete the user from the repository
	    userRepo.delete(user);

	    // Log the hard delete action in UserHistory
	    UserHistory userHis = new UserHistory();
	    userHis.setUserStatus(UserStatus.INACTIVE); // Using Enum for user status (INACTIVE)
	    userHis.setAction(Action.DELETED); // Using Enum for action (DELETED)
	    userHis.setRemark("User deleted Successfully And removed from DB (hard delete)");
	    userHis.setUser(user); // Setting user relation for history

	    // Save the user history
	    histRepo.save(userHis);

	    log.info("####### user hard deleted successfully #######");
	}

	
	
	private UserDetails getUserDetailsObject(UserDetailsDto userDto) {

//		traditional Method		
//		UserDetails user = new UserDetails();
		
//		user.setUser_id(userDto.getUser_id());
//		user.setUser_name(userDto.getUser_name());
//		user.setIsd_code(userDto.getIsd_code());
//		user.setMob_number(userDto.getMob_number());
//		user.setEmail_id(userDto.getEmail_id());
//		user.setGender(userDto.getGender());
//		user.setAge(userDto.getAge());
//		user.setActive_user(userDto.getActive_user());
//		user.setModified_date(userDto.getModified_date());
//		user.setUser_creation_date(userDto.getUser_creation_date());
//		user.setBirth_date(userDto.getBirth_date());
		
		UserDetails user = this.modelMapper.map(userDto, UserDetails.class);
		
		return user;
	}
	
	private UserDetailsDto getUserDtoObject( UserDetails user) {

//		traditional Method
//		UserDetailsDto userDto = new UserDetailsDto();
//		
//		userDto.setUser_id(user.getUser_id());
//		userDto.setUser_name(user.getUser_name());
//		userDto.setIsd_code(user.getIsd_code());
//		userDto.setMob_number(user.getMob_number());
//		userDto.setEmail_id(user.getEmail_id());
//		userDto.setGender(user.getGender());
//		userDto.setAge(user.getAge());
//		userDto.setActive_user(user.getActive_user());
//		userDto.setModified_date(user.getModified_date());
//		userDto.setUser_creation_date(user.getUser_creation_date());
//		userDto.setBirth_date(user.getBirth_date());
		
		UserDetailsDto userDto = this.modelMapper.map(user, UserDetailsDto.class);
				
		return userDto;
	}
	
	public List<UserHistoryDto> showUserHistory(Long user_id){
		
		UserDetails user = userRepo.findById(user_id).orElseThrow(
				() -> new ResourceNotFoundException("User ", "", 0)); 
		
		
		List<UserHistory> history = histRepo.findByUser(user);
		
		List<UserHistoryDto> historyDto = history.stream()
				.map(e -> this.gethistoryDtoObject(e))
				.collect(Collectors.toList());
		
		return historyDto;
//		return null;
		
	}

	private UserHistoryDto gethistoryDtoObject(UserHistory userHostory) {
		// TODO Auto-generated method stub
		UserHistoryDto DtoObject = this.modelMapper.map(userHostory,UserHistoryDto.class);
		return DtoObject;
	}

	private UserHistory gethistorObject(UserHistoryDto userHisDto) {
		// TODO Auto-generated method stub
		
		UserHistory userHis = this.modelMapper.map(userHisDto, UserHistory.class);
		return userHis;
	}

}



