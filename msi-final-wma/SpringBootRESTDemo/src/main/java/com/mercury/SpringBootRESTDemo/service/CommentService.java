package com.mercury.SpringBootRESTDemo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.mercury.SpringBootRESTDemo.bean.Comment;
import com.mercury.SpringBootRESTDemo.bean.User;
import com.mercury.SpringBootRESTDemo.dao.CommentDao;
import com.mercury.SpringBootRESTDemo.dao.UserDao;
import com.mercury.SpringBootRESTDemo.http.Response;

@Service
public class CommentService {

	
	@Autowired
	CommentDao commentDao;
	
	@Autowired
	UserDao userDao;
	
	public List<Comment> getCommentsByDoctorId(int id) {
		List<Comment> comments = commentDao.findAll();
		
		List<Comment> filteredComments = new ArrayList<>();
		
		for (Comment comment : comments) {
			if (comment.getDoctor_id() == id) {
				filteredComments.add(comment);
			}
		}
		return filteredComments;
	}
	
	@CacheEvict(value = "doctorsList", allEntries = true)
	public Response addComment(Comment comment, int id) {
		try {
			User doctor = userDao.findById(id).get();
		
			double total_rate = doctor.getDoctorDetail().getRate() * doctor.getDoctorDetail().getReviews_number() + comment.getPoint();
			int total_reviews_number = doctor.getDoctorDetail().getReviews_number() + 1;
			
			
			int scale  = 2; 
			int roundingMode = 4;
			BigDecimal bd = new BigDecimal((double)(total_rate / total_reviews_number));
			
			bd   =  bd.setScale(scale,roundingMode);  
			Double current_rate =  bd.doubleValue();
			
			System.out.println(current_rate);
			
			
			doctor.getDoctorDetail().setRate(current_rate);
			doctor.getDoctorDetail().setReviews_number(total_reviews_number);
			commentDao.save(comment);
			userDao.save(doctor);
			return new Response(true);
			
			
		} catch (Exception e) {
			return new Response(false);
		}
	}
}
