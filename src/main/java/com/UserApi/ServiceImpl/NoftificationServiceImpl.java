package com.UserApi.ServiceImpl;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.UserApi.CustomExceptions.ResourceNotFoundException;
import com.UserApi.Entities.NotificationDetails;
import com.UserApi.Entities.NotificationHistory;
import com.UserApi.Entities.UserDetails;
import com.UserApi.Entities.UserWaterConsumptionDetails;
import com.UserApi.Entities.Enums.Gender;
import com.UserApi.Repository.ConsumptnDetailsRepo;
import com.UserApi.Repository.NotiFicationDetailsRepo;
import com.UserApi.Repository.NotifHistoryRepo;
import com.UserApi.Repository.UserRepo;
import com.UserApi.Service.NoftificationService;
import com.UserApi.Service.UserService;
import com.UserApi.Service.Payloads.NotificationDetailsDto;
import com.UserApi.Service.Payloads.NotificationHistoryDto;
import com.UserApi.Service.Payloads.UserDetailsDto;
import com.UserApi.Service.Payloads.UserWaterConsumptionDetailsDto;
import com.UserApi.Utils.CalculationUtils;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;

@RequiredArgsConstructor
@Slf4j
@Service
public class NoftificationServiceImpl implements NoftificationService{

	public final NotifHistoryRepo  historyRepo;
	public final ConsumptnDetailsRepo consumptnDetailsRepo;
	public final NotiFicationDetailsRepo notifDetailsRepo;
	public final UserRepo userRepo;
	public final ModelMapper modelMapper;
	public final UserService userService;
	public final NotifHistoryRepo notifHistoryRepo;
	
	
	@Override
	@Transactional
	public boolean updateNotificationDetails(UserWaterConsumptionDetailsDto consumptionDetailsDto) {
		
		log.info(" entered in updateNofificationDetails for dto "+consumptionDetailsDto.toString());
		Long userId = consumptionDetailsDto.getUser().getUserId();
		log.info("getting user");
		 
		UserDetails user = userRepo.findById(userId).orElseThrow(
				 () -> new ResourceNotFoundException("User", "id", userId));
		
		UserWaterConsumptionDetails consumptionDetails = new UserWaterConsumptionDetails();
		
		Boolean isUserConsupmtionExist = consumptnDetailsRepo.existsById(CalculationUtils.nullToEmpty(user.getUserWaterConsumptionDetails().getId()));
		
		if(user.getUserWaterConsumptionDetails()!=null && isUserConsupmtionExist) {
			log.info("users notif details are already present: "+consumptionDetails.toString());
			log.info("users notif details from Database: "+consumptionDetails.toString());
			consumptionDetails = consumptnDetailsRepo.findById(user.getUserWaterConsumptionDetails().getId()).orElseThrow(
					() -> new ResourceNotFoundException("consumptionDetails", "id", userId)	);
		}else {		
		consumptionDetails = modelMapper.map(consumptionDetailsDto, UserWaterConsumptionDetails.class);
		consumptionDetails.setUser(user);
		}
		
		consumptionDetails.setAge(CalculationUtils.AgeCalculator(user.getBirthDate()));

		log.debug("Saving WaterConsumptionDetails");
		boolean status =  SetWaterConsumptionDetails(consumptionDetails);
	
		log.debug("saving consumptionDetails :"+consumptionDetails.toString());
		
	    consumptnDetailsRepo.save(consumptionDetails);
		
		return status;
	}

	@Transactional
	private boolean SetWaterConsumptionDetails(UserWaterConsumptionDetails consumptionDetails) {
		
		
		try{
			
//			deleting privious data
			
			notifDetailsRepo.deleteAllByUser(consumptionDetails.getUser());
			
			
	    consumptionDetails.setDailyGoal(GetDailyComsumpTion(consumptionDetails));
		
//	    calculating total wakeup time
	    LocalTime wakeupTime = consumptionDetails.getWakeupTime();
	    LocalTime bedTime = consumptionDetails.getBedTime();
		
	    Duration duration = Duration.between(wakeupTime, bedTime);
		double totalWakeTime = duration.toMinutes();
		
//		calculatint total times of notif
		int notifNum = (int) (consumptionDetails.getDailyGoal()/consumptionDetails.getCupSize());
	
//		calculating time interval
		int intervalInMin = (int) (totalWakeTime/notifNum);
		
		Double amtOfWater=(double) 0;
		LocalTime notifTime = consumptionDetails.getWakeupTime();
	
		 log.debug("details going to update: 1. dailyGoal :"+consumptionDetails.getDailyGoal()
		 +" | 2. totalWakeTime: " + totalWakeTime 
		 +" | 3. notifNum: "+ notifNum 
		 +" | 4. intervalInMin: "+ intervalInMin 
		 +" | 5. CupSize: "+ consumptionDetails.getCupSize());
		 
		for(int i=0; i<notifNum;i++) {
			
			NotificationDetails notificationDetails = new NotificationDetails();

			notificationDetails.setNotifOrder(i);
			notificationDetails.setCupSize(consumptionDetails.getCupSize());
			notificationDetails.setUser(consumptionDetails.getUser());
			notificationDetails.setTimestampForNotifi(notifTime);
			notifTime=notifTime.plusMinutes(intervalInMin);
			notificationDetails.setAmtOfWater(amtOfWater+consumptionDetails.getCupSize());
			log.debug("inserting notification schuduler Details: "+notificationDetails.toString());
			notifDetailsRepo.save(notificationDetails);
			
			
		}
		
		log.info("notification schuduler Details successfully inserted ");
		return true;
		
		
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	private double GetDailyComsumpTion(UserWaterConsumptionDetails consumptionDetails) { 

		 log.debug("calculating Daily Water ComsumpTion");
		// Base water requirement (in ounces)
        double weightLbs = consumptionDetails.getWeight() * 2.20462; // Convert kg to lbs
        double baseWaterOunces = weightLbs * 0.5;

        // Additional water for exercise
        double exerciseWaterOunces = (15 / 30) * 12;

        // Adjustments based on gender
        if (Gender.MALE.equals(consumptionDetails.getGender())) {
            baseWaterOunces += 10; // Men need slightly more water
        }

        // Body Surface Area (BSA) factor
        double bsa = Math.sqrt((consumptionDetails.getHeight() * consumptionDetails.getWeight()) / 3600);
        double bsaFactorOunces = bsa * 10; // Adjust based on BSA

        // Total water requirement (in ounces)
        double totalWaterOunces = baseWaterOunces + exerciseWaterOunces + bsaFactorOunces;

        // Convert to liters
        double totalWaterML = totalWaterOunces * 0.0295735 * 1000;

		 log.debug("Daily Water ComsumpTion :" + totalWaterML +" | for user: "+consumptionDetails.toString());
		 
        return totalWaterML;
        
        }

	@Override
	public UserWaterConsumptionDetailsDto getWaterConsumptionDetails(Long userId) {

		log.info("Start of method getWaterConsumptionDetails");

		log.info("getting user from user id :" + userId);
		
		UserDetails user =  userRepo.findById(userId).orElseThrow(
				()-> new ResourceNotFoundException("user_Details","User",userId));


		log.info(" user :" + userId + " found | getting consumption details. ");
		
		UserWaterConsumptionDetails consumptionDetails = consumptnDetailsRepo.findById(user.getUserWaterConsumptionDetails().getId()).orElseThrow(
				() -> new ResourceNotFoundException("consumptionDetails", "id", userId)	);
		
		log.info("consumption details found : "+consumptionDetails.toString());
		log.info("end of method getWaterConsumptionDetails");
		return modelMapper.map(consumptionDetails,UserWaterConsumptionDetailsDto.class);
	}

	@Override
	public List<NotificationDetailsDto> getNotificationDetails(Long userId) {

		log.info("Start of method getNotificationDetails");

		log.info("getting user from user id :" + userId);
		
		UserDetails user =  userRepo.findById(userId).orElseThrow(
				()-> new ResourceNotFoundException("user_Details","User",userId));


		log.info(" user :" + userId + " found | getting Notification details. ");
		
		List<Long> notifDetailsIds = user.getNotificationDetails().stream()
													.map(d-> d.getId())
													.collect(Collectors.toList()); 
		
		List<NotificationDetails> notificationDetails  = notifDetailsRepo.findByIdIn(notifDetailsIds); //get all ids of notification details from one to many mappings
		
		log.info("consumption details found : "+notificationDetails.toString());
		log.info("end of method getNotificationDetails");
		
		List<NotificationDetailsDto> notificationDetailsDtos = new ArrayList<NotificationDetailsDto>();
		
		for (NotificationDetails notificationDetail : notificationDetails) {
		 notificationDetailsDtos.add( modelMapper.map(notificationDetail, NotificationDetailsDto.class));
		}
		 
		 return notificationDetailsDtos;
	}

	@Override
	public List<NotificationHistoryDto> getNotificationHistory(Long userId) {

		log.info("Start of method getNotificationHistory");

		log.info("getting user from user id :" + userId);
		
		UserDetails user =  userRepo.findById(userId).orElseThrow(
				()-> new ResourceNotFoundException("user_Details","User",userId));


		log.info(" user :" + userId + " found | getting Notification details. ");
		
		List<Long> notifHistoryIds = user.getNotificationHistory()
													.stream()
													.map(d-> d.getId())
													.collect(Collectors.toList()); 
		
		List<NotificationHistory> notificationHistories  = notifHistoryRepo.findByIdIn(notifHistoryIds); //get all ids of notification details from one to many mappings
		
		log.info("consumption details found : "+notificationHistories.toString());
		log.info("end of method getNotificationHistory");
		
		List<NotificationHistoryDto> notificationHistoryDtos = new ArrayList<NotificationHistoryDto>();
		
		for (NotificationHistory notificationHistory : notificationHistories) {
			notificationHistoryDtos.add( modelMapper.map(notificationHistory, NotificationHistoryDto.class));
		}
		 
		 return notificationHistoryDtos;
	}

}
