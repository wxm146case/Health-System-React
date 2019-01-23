package com.mercury.SpringBootRESTDemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.SpringBootRESTDemo.bean.Comment;

public interface CommentDao extends JpaRepository<Comment, Integer>{
	
	
}
