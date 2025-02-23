package com.UserApi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UserApi.Entities.UserDetails;
import com.UserApi.Entities.UserHistory;

public interface HistoryRepo  extends JpaRepository<UserHistory, Long> {

	List<UserHistory> findByUser(UserDetails user);

	//List<UserHistory> findByUserId(Integer user_Id);


}
