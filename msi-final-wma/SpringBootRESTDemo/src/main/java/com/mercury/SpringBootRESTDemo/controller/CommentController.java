package com.mercury.SpringBootRESTDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.SpringBootRESTDemo.bean.Comment;
import com.mercury.SpringBootRESTDemo.http.Response;
import com.mercury.SpringBootRESTDemo.service.CommentService;

@RestController
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@GetMapping("/comments/{id}")
	public List<Comment> getComments(@PathVariable int id) {
		return commentService.getCommentsByDoctorId(id);
	}
	
	@PostMapping("/comments/{id}")
	public Response addComment(@RequestBody Comment comment, @PathVariable int id) {
		return commentService.addComment(comment, id);
	}

}
