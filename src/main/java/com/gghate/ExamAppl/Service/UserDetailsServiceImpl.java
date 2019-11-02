package com.gghate.ExamAppl.Service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import com.gghate.ExamAppl.Security.AccountCredentials;



@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	private ApplicationUserRepositor applicationUserRepository;

    public UserDetailsServiceImpl(ApplicationUserRepositor applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		AccountCredentials applicationUser = applicationUserRepository.findByUsername(username);
		
        if (applicationUser == null) {
        	System.out.println("User not found");
            throw new UsernameNotFoundException(username);
            
        }
        System.out.println("User found"+applicationUser.getUsername());
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), new ArrayList<>());
	}

	

}
