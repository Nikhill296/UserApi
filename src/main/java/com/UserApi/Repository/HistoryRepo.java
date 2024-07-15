package com.UserApi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UserApi.Entities.UserHistory;

public interface HistoryRepo  extends JpaRepository<UserHistory, Integer> {

	List<UserHistory> findByUserId(Integer user_Id);


}
