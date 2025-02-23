package com.UserApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UserApi.Entities.UserDetails;
import com.UserApi.Entities.UserWaterConsumptionDetails;
import com.UserApi.Service.Payloads.UserWaterConsumptionDetailsDto;

public interface ConsumptnDetailsRepo extends JpaRepository<UserWaterConsumptionDetails, Long> {

//	boolean existsByUserDetails(UserDetails user);
//
// UserWaterConsumptionDetails getByUserDetails(UserDetails user);

}
