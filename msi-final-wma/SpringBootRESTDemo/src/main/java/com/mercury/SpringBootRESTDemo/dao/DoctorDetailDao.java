package com.mercury.SpringBootRESTDemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.SpringBootRESTDemo.bean.DoctorDetail;

public interface DoctorDetailDao extends JpaRepository<DoctorDetail, Integer> {

}
