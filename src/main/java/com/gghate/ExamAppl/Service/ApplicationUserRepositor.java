package com.gghate.ExamAppl.Service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.gghate.ExamAppl.Security.AccountCredentials;

@Service
public interface ApplicationUserRepositor extends JpaRepository<AccountCredentials,Integer> {
   
	AccountCredentials findByUsername(String username);
	
	List<AccountCredentials> findByEnabled(Boolean enabled );
	@Query("select n from AccountCredentials n where n.std=:std order by n.firstName")
	List<AccountCredentials> findByStd(String std );
}
