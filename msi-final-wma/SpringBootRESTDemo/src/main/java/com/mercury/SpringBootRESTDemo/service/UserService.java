package com.mercury.SpringBootRESTDemo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.SpringBootRESTDemo.bean.DoctorDetail;
import com.mercury.SpringBootRESTDemo.bean.User;
import com.mercury.SpringBootRESTDemo.bean.UserDetail;
import com.mercury.SpringBootRESTDemo.bean.UserProfile;
import com.mercury.SpringBootRESTDemo.dao.DoctorDetailDao;
import com.mercury.SpringBootRESTDemo.dao.UserDao;
import com.mercury.SpringBootRESTDemo.dao.UserDetailDao;
import com.mercury.SpringBootRESTDemo.http.Response;
import com.mercury.SpringBootRESTDemo.mail.EmailService;
import com.mercury.SpringBootRESTDemo.security.SecurityUtils;

@Service
@Transactional
public class UserService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserDao userDao;
	
	@Autowired
	UserDetailDao userDetailDao;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	DoctorDetailDao doctorDetailDao;

	public Response register(User user) {
		// TODO: validation.		
		try {
			if (userDao.findByUsername(user.getUsername()) != null) {
				throw new Exception("Username existed!!");
			}
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			List<UserProfile> profiles = new ArrayList<UserProfile>();
			profiles.add(new UserProfile(2));
			user.setProfiles(profiles);
			user.setUsername(user.getUsername());
//			userDao.save(user);
			User savedUser = userDao.findByUsername(user.getUsername());
			UserDetail userDetail = user.getUserDetail();
			userDetail.setUser(savedUser);
//			userDetailDao.save(userDetail);
			

			
			// TODO: send email. assume username is the email for now
//			emailService.sendSimpleMessage(user.getUsername(), "Account Created", user.getUsername() + ", your account is created!");
			return new Response(true);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new Response(false, 400, e.getMessage());
		}
	}
	
	@Cacheable("doctorsList")
	public List<User> getDoctors() {
		System.out.println("******getDoctors");
		List<User> users = userDao.findAll();
		List<User> doctors = new ArrayList<>();
		
		for (User user : users) {
			
			if (user.getProfiles().get(0).getType().equals("ROLE_DOCTOR")) {
				doctors.add(user);
			}
		}
		System.out.println(doctors);
		return doctors;
	}
	
	public Response changePassword(User user, Authentication authentication) {
		if(user.getUsername().equals(authentication.getName()) || SecurityUtils.isAdmin(authentication.getAuthorities())) {
			User u = userDao.findByUsername(user.getUsername());
			u.setPassword(passwordEncoder.encode(user.getPassword()));
			userDao.save(u);
		}else {
			//TODO: Not authorize to update password if not current loggedin user or admin.
			return new Response(false);
		}
		return new Response(true);
	}
	
	
	@CacheEvict(value = "doctorsList", allEntries = true)
	public Response onlineStatus(int id) {
		DoctorDetail doctorDetail = doctorDetailDao.findById(id).get();
		doctorDetail.setStatus("on");
		doctorDetailDao.save(doctorDetail);
		return new Response(true);
	}
	
	@CacheEvict(value = "doctorsList", allEntries = true)
	public Response offlineStatus(int id) {
		DoctorDetail doctorDetail = doctorDetailDao.findById(id).get();
		doctorDetail.setStatus("off");
		doctorDetailDao.save(doctorDetail);
		return new Response(true);
	}
	
	public Response deleteUser(int id) {
		if(userDao.findById(id).get() != null) {
			userDao.deleteById(id);
			return new Response(true);
		}else {
			return new Response(false, "User is not found!");
		}
	}

}
