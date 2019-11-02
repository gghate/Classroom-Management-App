package com.gghate.ExamAppl.Service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.gghate.ExamAppl.Entity.Notification;

@Service
public interface notificationRepository extends JpaRepository<Notification, Integer> {

	

	@Query("select n from Notification n where n.id>:id and n.std=:std order by n.id desc")
	public List<Notification> getNewNotification(int id,String std);
	
}
