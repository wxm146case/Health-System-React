package com.mercury.SpringBootRESTDemo.dao;

import org.springframework.data.repository.CrudRepository;

import com.mercury.SpringBootRESTDemo.bean.Appointment;

public interface AppointmentDao extends CrudRepository<Appointment, Integer> {  
}
