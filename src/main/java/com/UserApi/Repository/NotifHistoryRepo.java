package com.UserApi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UserApi.Entities.NotificationDetails;
import com.UserApi.Entities.NotificationHistory;

public interface NotifHistoryRepo extends JpaRepository<NotificationHistory, Long>{

	List<NotificationHistory> findByIdIn(List<Long> notifDetailsIds);

}
