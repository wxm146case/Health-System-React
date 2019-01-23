package com.mercury.SpringBootRESTDemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.SpringBootRESTDemo.bean.DoctorDetailPatient;

public interface DoctorDetailPatientDao extends JpaRepository<DoctorDetailPatient, Integer> {

}
