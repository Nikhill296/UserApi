package com.UserApi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UserApi.Entities.NotificationHistory;
import com.UserApi.Service.NoftificationService;
import com.UserApi.Service.Payloads.ApiResponce;
import com.UserApi.Service.Payloads.NotificationDetailsDto;
import com.UserApi.Service.Payloads.NotificationHistoryDto;
import com.UserApi.Service.Payloads.UserDetailsDto;
import com.UserApi.Service.Payloads.UserWaterConsumptionDetailsDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/NotifDetails")
//@RequiredArgsConstructor
@Slf4j
public class NotificationDetailsController {
	
	@Autowired
	private NoftificationService notificationService;
	
	//POST - 
	@PostMapping("/updateNotif")
	public ResponseEntity<ApiResponce> updateWaterConsumptionDetails(@Valid @RequestBody UserWaterConsumptionDetailsDto ConsumptionDetailsDto){
		log.info("entered in updateWaterConsumptionDetails controoler ");
//		log.info("request body :: >>>>>>>> "+ userDto.toString());
				boolean updated = notificationService.updateNotificationDetails(ConsumptionDetailsDto);
		return (updated)?
				new ResponseEntity<ApiResponce>(new ApiResponce(null,"Consumption Details Updated", true), HttpStatus.CREATED): 
				new ResponseEntity<ApiResponce>(new ApiResponce(null,"Consumption Details not Updated", false), HttpStatus.REQUEST_TIMEOUT);
		
	}
	
	//GET - 
	@GetMapping("/getConsumptionDetails/{userId}")
	public ResponseEntity<UserWaterConsumptionDetailsDto> getWaterConsumptionDetails(@PathVariable Long userId){
		log.info("entered in updateWaterConsumptionDetails controoler ");
//		log.info("request body :: >>>>>>>> "+ userDto.toString());
//				boolean updated = notificationService.updateNotificationDetails(ConsumptionDetailsDto);
				
				UserWaterConsumptionDetailsDto consumptionDetailsDto = notificationService.getWaterConsumptionDetails(userId);;
				
		return new ResponseEntity<UserWaterConsumptionDetailsDto>(consumptionDetailsDto,HttpStatus.FOUND); 
				
//				ResponseEntity<UserWaterConsumptionDetailsDto>(new ApiResponce(consumptionDetailsDto, HttpStatus.FOUND); 
	}
	

	//GET - 
		@GetMapping("/getNotifDetails/{userId}")
		public ResponseEntity<List<NotificationDetailsDto>> getWaterNotifDetails(@PathVariable Long userId){
			log.info("entered in updateWaterConsumptionDetails controoler ");
//			log.info("request body :: >>>>>>>> "+ userDto.toString());
//					boolean updated = notificationService.updateNotificationDetails(ConsumptionDetailsDto);
					
			List<NotificationDetailsDto> noftifDetailsDto = notificationService.getNotificationDetails(userId);;
					
			return new ResponseEntity<List<NotificationDetailsDto>>(noftifDetailsDto,HttpStatus.FOUND); 
					
//					ResponseEntity<UserWaterConsumptionDetailsDto>(new ApiResponce(consumptionDetailsDto, HttpStatus.FOUND); 
		}
		
		//GET - 
		@GetMapping("/getNotifHistory/{userId}")
		public ResponseEntity<List<NotificationHistoryDto>> getWaterNotifHistory(@PathVariable Long userId){
			log.info("entered in updateWaterConsumptionDetails controoler ");
//			log.info("request body :: >>>>>>>> "+ userDto.toString());
//					boolean updated = notificationService.updateNotificationDetails(ConsumptionDetailsDto);
					
			List<NotificationHistoryDto> noftifHistoryDto = notificationService.getNotificationHistory(userId);;
					
			return new ResponseEntity<List<NotificationHistoryDto>>(noftifHistoryDto,HttpStatus.FOUND); 
					
//					ResponseEntity<UserWaterConsumptionDetailsDto>(new ApiResponce(consumptionDetailsDto, HttpStatus.FOUND); 
		}
		
}
