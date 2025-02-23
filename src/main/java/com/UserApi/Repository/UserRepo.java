package com.UserApi.Repository;

import com.UserApi.Entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserDetails, Long>{

	
	

}
