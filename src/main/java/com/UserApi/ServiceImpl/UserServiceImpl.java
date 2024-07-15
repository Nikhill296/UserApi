package com.UserApi.ServiceImpl;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UserApi.CustomExceptions.ResourceNotFoundException;
import com.UserApi.Entities.UserDetails;
import com.UserApi.Entities.UserHistory;
import com.UserApi.Repository.HistoryRepo;
import com.UserApi.Repository.UserRepo;
import com.UserApi.Service.UserService;
import com.UserApi.Service.Payloads.UserDetailsDto;
import com.UserApi.Service.Payloads.UserHistoryDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
		
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private HistoryRepo histRepo;

	@Override
	public UserDetailsDto createUserDto(UserDetailsDto UserDto) {
		
		
//		System.out.println("userdto in request >>>>>>> "+UserDto.toString());
		UserDetails user = this.getUserDetailsObject(UserDto);
		user.setUser_creation_date(LocalDateTime.now());
		
		UserDetails savedUser = this.userRepo.save(user);

		UserHistoryDto userHisDto = new UserHistoryDto();
		
		userHisDto.setUserId(savedUser.getUser_id());
		userHisDto.setUser_status("UC");
		userHisDto.setRemark("User Created Successfully");

		UserHistory userHis = this.gethistorObject(userHisDto);
		
		histRepo.save(userHis);
		
		return  this.getUserDtoObject(savedUser);
	}



	@Override
	public UserDetailsDto updateUserDto(UserDetailsDto userDtoReq, Integer userId) {

		 UserDetails user = new UserDetails();
		 UserDetails updatedUser = new  UserDetails();
		 

			  user = userRepo.findById(userId)
					  .orElseThrow(()-> new ResourceNotFoundException("user_Details","User",userId));
		  
//			  System.out.println("\n userDtoReq of request" + userDtoReq);
//			  System.out.println("\n user before update" + user);
			  
			  user =  this.updateUserDataIfInReq(userDtoReq,user);
			  
//			  System.out.println("\n user after updateDATA " + user);
			  
		      updatedUser = userRepo.save(user);
		      
//			  System.out.println("\n user after Update Query  " + updatedUser);
				UserHistory userHis = new UserHistory();
				
				userHis.setUserId(user.getUser_id());
				userHis.setUser_status("UU");
				userHis.setRemark("User updated Successfully");
				histRepo.save(userHis);

			  
			  System.out.println("####### user details updated successfully #######");
	 
		
		return this.getUserDtoObject(updatedUser);
	}

	private UserDetails updateUserDataIfInReq(UserDetailsDto userDto, UserDetails user) {

		try {
			
			if( userDto.getUser_name() != null && !userDto.getUser_name().isEmpty())
				user.setUser_name(userDto.getUser_name());

			if( userDto.getIsd_code() != null && !userDto.getIsd_code().isEmpty() )
			user.setIsd_code(userDto.getIsd_code());

			if( userDto.getMob_number() != null && !userDto.getMob_number().isEmpty())
			user.setMob_number(userDto.getMob_number());

			if( userDto.getEmail_id() != null && !userDto.getEmail_id().isEmpty() )
			user.setEmail_id(userDto.getEmail_id());

			if( userDto.getGender() != null && !userDto.getGender().isEmpty())
			user.setGender(userDto.getGender());

			if( userDto.getAge() != null && !userDto.getAge().isEmpty() )
			user.setAge(userDto.getAge());

			if( userDto.getBirth_date() != null && !userDto.getBirth_date().isEmpty())
			user.setBirth_date(userDto.getBirth_date());
			
			
			
		} catch (Exception e) {
			System.out.println("Exception in updateUserDataIfInReq  " + e.getMessage());
		}
		return user;

	}

	@Override
	public UserDetailsDto getUserById(Integer userId) {

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
			
			System.out.println("####### All users Featched successfully #######");

		}catch (Exception e) {
			
			System.out.println("Exception in getting All user " + e.getMessage());

		}
		
		return userDtoList;
	}

	@Override
	public void deleteUser(Integer userId) {

		 UserDetails user = new UserDetails();
		 UserDetails updatedUser = new  UserDetails();
		 
		 
			  user = userRepo.findById(userId)
					  .orElseThrow(()-> new ResourceNotFoundException("user_Details","User",userId));
		 
			  user.setActive_user("N");
		      updatedUser = userRepo.save(user);
		      
				UserHistory userHis = new UserHistory();
				
				userHis.setUserId(user.getUser_id());
				userHis.setUser_status("UD");
				userHis.setRemark("User deleted Successfully");
				histRepo.save(userHis);

			  
			  
			  System.out.println("####### user deleted updated successfully #######");
			  
		
	}
	
	@Override
	public void hardDeleteUser(Integer userId) {

		 UserDetails user = new UserDetails();
		 
		 
			  user = userRepo.findById(userId)
					  .orElseThrow(()-> new ResourceNotFoundException("user_Details","User",userId));
		 
		      userRepo.delete(user);
		      
				UserHistory userHis = new UserHistory();
				
				userHis.setUserId(user.getUser_id());
				userHis.setUser_status("UHD");
				userHis.setRemark("User deleted Successfully And removed from db");
				histRepo.save(userHis);
				
  
			  System.out.println("####### user deleted updated successfully #######");
			  
		
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
	
	public List<UserHistoryDto> showUserHistory(Integer user_id){
		
		
		List<UserHistory> history = histRepo.findByUserId(user_id);
		
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



