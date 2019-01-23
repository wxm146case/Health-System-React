package com.mercury.SpringBootRESTDemo.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "msi_doctor_detail")
public class DoctorDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MSI_DOCTOR_DETAIL_SEQ1_GEN")
	@SequenceGenerator(name = "MSI_DOCTOR_DETAIL_SEQ1_GEN", sequenceName = "MSI_DOCTOR_DETAIL_SEQ1", allocationSize = 1)
	int id;
	
	@Column
	String location;
	
	
	@Column
	String department;
	
	@Column
	String type;
	
	@Column
	String language;
	
	@Column
	String photo;
	
	@Column
	Double rate;
	
	@Column
	int reviews_number;
	
	@Column
	String status;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "msi_doctor_detail_patient", joinColumns = {
			@JoinColumn(name = "doctor_detail_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "patient_id", referencedColumnName = "id") })
	private Set<User> patients = new HashSet<User>();
	
	
	@OneToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	User user;
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<User> getPatients() {
		return patients;
	}

	public void setPatients(Set<User> patients) {
		this.patients = patients;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public int getReviews_number() {
		return reviews_number;
	}

	public void setReviews_number(int reviews_number) {
		this.reviews_number = reviews_number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "DoctorDetail [id=" + id + ", location=" + location + ", department=" + department + ", type=" + type
				+ ", language=" + language + ", photo=" + photo + "]";
	}

	
	
	
}
