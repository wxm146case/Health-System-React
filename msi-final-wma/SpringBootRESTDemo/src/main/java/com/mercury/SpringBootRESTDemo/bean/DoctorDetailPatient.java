package com.mercury.SpringBootRESTDemo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "msi_doctor_detail_patient")
public class DoctorDetailPatient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MSI_DOCTOR_DETAIL_PATIENT_SEQ_GEN")
	@SequenceGenerator(name = "MSI_DOCTOR_DETAIL_PATIENT_SEQ_GEN", sequenceName = "MSI_DOCTOR_DETAIL_PATIENT_SEQ", allocationSize = 1)
	private int id;
	
	
	@Column
	private int doctor_detail_id;
	
	
	@Column
	private int patient_id;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getDoctor_detail_id() {
		return doctor_detail_id;
	}


	public void setDoctor_detail_id(int doctor_detail_id) {
		this.doctor_detail_id = doctor_detail_id;
	}


	public int getPatient_id() {
		return patient_id;
	}


	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}


	@Override
	public String toString() {
		return "DoctorDetailPatient [id=" + id + ", doctor_detail_id=" + doctor_detail_id + ", patient_id=" + patient_id
				+ "]";
	}
	
	

}
