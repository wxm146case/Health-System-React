package com.mercury.SpringBootRESTDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.SpringBootRESTDemo.http.Response;
import com.mercury.SpringBootRESTDemo.service.UserService;

@RestController
public class DoctorDetailController {
	
	@Autowired
	UserService userService;
	
	@PutMapping("/doctor-on/{id}")
	public Response DoctorOn(@PathVariable int id) {
		return userService.onlineStatus(id);
	}
	
	@PutMapping("/doctor-off/{id}")
	public Response DoctorOff(@PathVariable int id) {
		return userService.offlineStatus(id);
	}
	
}
