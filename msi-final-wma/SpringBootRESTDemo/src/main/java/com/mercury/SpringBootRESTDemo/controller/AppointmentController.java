package com.mercury.SpringBootRESTDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.SpringBootRESTDemo.bean.ResponseAppointment;
import com.mercury.SpringBootRESTDemo.http.Response;
import com.mercury.SpringBootRESTDemo.service.AppointmentService;

@RestController
public class AppointmentController {
	
	@Autowired
	AppointmentService appointmentService;
	
	@GetMapping("/appointments")
	@PreAuthorize("hasAnyAuthority('ROLE_DOCTOR', 'ROLE_PATIENT')")
	public List<ResponseAppointment> getAppointments(Authentication authentication) {
		return appointmentService.getAppointments(authentication);
	}
	
	@PostMapping("/appointments")
	public Response postAppointment(@RequestBody ResponseAppointment responseAppointment, Authentication authentication) {
		return appointmentService.addAppointment(responseAppointment, authentication);
	}
	
	
	@PutMapping("/approve-appointment")
	public Response approveAppointment(@RequestBody ResponseAppointment responseAppointment) {
		return appointmentService.approveAppointment(responseAppointment);
	}
	
	@PutMapping("/appointments")
	public Response putAppointment(@RequestBody ResponseAppointment responseAppointment) {
		return appointmentService.editAppointment(responseAppointment);
	}
	
	@PutMapping("/close-appointment")
	public Response closeAppointment(@RequestBody ResponseAppointment responseAppointment) {
		return appointmentService.closeAppointment(responseAppointment);
	}
	
	@DeleteMapping("/appointments/{id}")
	public Response deleteAppointment(@PathVariable int id) {
		return appointmentService.deleteAppointment(id);
	}
	
}
