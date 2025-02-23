package com.UserApi.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UserApi.Entities.NotificationDetails;
import com.UserApi.Entities.UserDetails;

public interface NotiFicationDetailsRepo extends JpaRepository<NotificationDetails, Long> {

	void deleteAllByUser(UserDetails user);

	Optional<NotificationDetails> findByUser(UserDetails user);

//	List<NotificationDetails> findAllByUserId(Long userId);

	List<NotificationDetails> findByIdIn(List<Long> notifDetailsIds);



}
