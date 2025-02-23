package com.UserApi.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.UserApi.Service.Payloads.NotificationDetailsDto;
import com.UserApi.Service.Payloads.NotificationHistoryDto;
import com.UserApi.Service.Payloads.UserWaterConsumptionDetailsDto;

@Service
public interface NoftificationService {

	boolean updateNotificationDetails(UserWaterConsumptionDetailsDto ConsumptionDetailsDto);

	UserWaterConsumptionDetailsDto getWaterConsumptionDetails(Long userId);

	List<NotificationDetailsDto> getNotificationDetails(Long userId);

	List<NotificationHistoryDto> getNotificationHistory(Long userId);
}

