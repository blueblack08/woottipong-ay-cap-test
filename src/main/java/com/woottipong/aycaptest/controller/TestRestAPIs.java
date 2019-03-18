package com.woottipong.aycaptest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woottipong.aycaptest.model.User;
import com.woottipong.aycaptest.security.services.MemberServices;

@RestController
public class TestRestAPIs {
	
	@Autowired
	private MemberServices memberServices;
	
	@GetMapping("/api/silver/member")
	@PreAuthorize("hasRole('SILVER') or hasRole('GOLD') or hasRole('PLATINUM')")
	public User userAccess() {
		return memberServices.getMemberDetails();
	}

	@GetMapping("/api/gold/member")
	@PreAuthorize("hasRole('GOLD') or hasRole('PLATINUM')")
	public User projectManagementAccess() {
		return memberServices.getMemberDetails();
	}
	
	@GetMapping("/api/platinum/member")
	@PreAuthorize("hasRole('PLATINUM')")
	public User adminAccess() {
		return memberServices.getMemberDetails();
	}
}