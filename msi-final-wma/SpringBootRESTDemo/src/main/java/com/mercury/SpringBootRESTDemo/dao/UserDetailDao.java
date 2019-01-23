package com.mercury.SpringBootRESTDemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.SpringBootRESTDemo.bean.UserDetail;

public interface UserDetailDao extends JpaRepository<UserDetail, Integer> {
	UserDetail findByname(String name); 
}
