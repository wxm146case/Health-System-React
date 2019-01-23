package com.mercury.SpringBootRESTDemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.mercury.SpringBootRESTDemo.bean.Appointment;
import com.mercury.SpringBootRESTDemo.bean.DoctorDetailPatient;
import com.mercury.SpringBootRESTDemo.bean.ResponseAppointment;
import com.mercury.SpringBootRESTDemo.bean.User;
import com.mercury.SpringBootRESTDemo.dao.AppointmentDao;
import com.mercury.SpringBootRESTDemo.dao.DoctorDetailPatientDao;
import com.mercury.SpringBootRESTDemo.dao.UserDao;
import com.mercury.SpringBootRESTDemo.dao.UserDetailDao;
import com.mercury.SpringBootRESTDemo.http.Response;

@Service
public class AppointmentService {
	
	@Autowired
	AppointmentDao appointmentDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserDetailDao userDetailDao;
	
	@Autowired
	DoctorDetailPatientDao doctorDetailPatientDao;
	
	
	@Cacheable("appointmentsList")
	public List<ResponseAppointment> getAppointments(Authentication authentication) {
		User user = userDao.findByUsername(authentication.getName());
		List<ResponseAppointment> appointments = new ArrayList<>();
		
		for (Appointment a : user.getAppointments()) {
			ResponseAppointment resApp = new ResponseAppointment();
			List<User> users = a.getUsers();
			for (User u : users) {
				if (u.getProfiles().get(0).getId() == 1) {
					resApp.setDoctor_name(u.getUserDetail().getName());
					resApp.setDoctor_id(u.getId());
				} 
				if (u.getProfiles().get(0).getId() == 2) {
					resApp.setPatient_name(u.getUserDetail().getName());
				}
			}
			resApp.setId(a.getId());
			resApp.setNote(a.getNote());
			resApp.setPrevious_diagnosis(a.getPrevious_diagnosis());
			resApp.setTime(a.getTime());
			resApp.setReason(a.getReason());
			resApp.setPrescription(a.getPrescription());
			resApp.setStatus(a.getStatus());

			appointments.add(resApp);
		}
		return appointments;
	}
	
	@CacheEvict(value = "appointmentsList", allEntries = true)
	public Response addAppointment(ResponseAppointment responseAppointment, Authentication authentication) {
		try {
			Appointment appointment = new Appointment();
			appointment.setNote(responseAppointment.getNote());
			appointment.setPrevious_diagnosis(responseAppointment.getPrevious_diagnosis());
			appointment.setReason(responseAppointment.getReason());
			
			appointment.setStatus(responseAppointment.getStatus());
		
			appointment.setTime(responseAppointment.getTime());
			
			
			List<User> users = new ArrayList<User>();
			
			User doctor = userDao.findById(responseAppointment.getDoctor_id()).get();

			
			
			User patient = userDao.findByUsername(authentication.getName());
			
			users.add(doctor);
			users.add(patient);
			
			appointment.setUsers(users);
			
			appointmentDao.save(appointment);
			return new Response(true);
			
		} catch (Exception e) {
			return new Response(false);
		}
	}
	
	@CacheEvict(value = "appointmentsList", allEntries = true)
	public Response approveAppointment(ResponseAppointment responseAppointment) {
		
		try {
			Appointment appointment = appointmentDao.findById(responseAppointment.getId()).get();
			appointment.setStatus("approved");
			appointmentDao.save(appointment);
			
			return new Response(true);
		} catch (Exception e) {
			return new Response(false);
		}	
	}
	@CacheEvict(value = "appointmentsList", allEntries = true)
	public Response editAppointment(ResponseAppointment responseAppointment) {
			
			try {
				Appointment appointment = appointmentDao.findById(responseAppointment.getId()).get();
				appointment.setPrescription(responseAppointment.getPrescription());
				appointmentDao.save(appointment);
				
				return new Response(true);
			} catch (Exception e) {
				return new Response(false);
			}	
		}
		
		@CacheEvict(value = "appointmentsList", allEntries = true)
		public Response closeAppointment(ResponseAppointment responseAppointment) {
				
				try {
					Appointment appointment = appointmentDao.findById(responseAppointment.getId()).get();
					appointment.setStatus("ongoing");
					appointmentDao.save(appointment);
					
					User doctor = userDetailDao.findByname(responseAppointment.getDoctor_name()).getUser();
					User patient = userDetailDao.findByname(responseAppointment.getPatient_name()).getUser();
					
				
					
					DoctorDetailPatient doctorPatient =  new DoctorDetailPatient();
					
					doctorPatient.setDoctor_detail_id(doctor.getId());
					doctorPatient.setPatient_id(patient.getId());

					System.out.println("***********************" + doctorPatient);
					
					doctorDetailPatientDao.save(doctorPatient);
					
					return new Response(true);
				} catch (Exception e) {
					return new Response(false);
				}	
			}
	
	
		@CacheEvict(value = "appointmentsList", allEntries = true)
		public Response deleteAppointment(int id) {
			try {
				Appointment appointment = appointmentDao.findById(id).get();
				appointmentDao.delete(appointment);
				return new Response(true);
			} catch (Exception e) {
				return new Response(false);
			}
		}
}
