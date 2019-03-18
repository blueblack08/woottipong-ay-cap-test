package com.woottipong.aycaptest.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.woottipong.aycaptest.model.User;
import com.woottipong.aycaptest.repository.UserRepository;

@Service
public class MemberServices {
	
	@Autowired 
	private UserRepository userRepo;
	
	public User getMemberDetails() {
		try {

			UserPrinciple principal = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Optional<User> user = userRepo.findByUsername(principal.getUsername());
			return user.get();
		} catch(Exception ex) {
			return null;
		}
	}
}
