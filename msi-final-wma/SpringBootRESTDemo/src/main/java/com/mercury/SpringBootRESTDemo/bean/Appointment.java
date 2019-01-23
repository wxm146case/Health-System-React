package com.mercury.SpringBootRESTDemo.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;



@Entity
@Table(name = "msi_appointment")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MSI_APPOINTMENT_SEQ1_GEN")
	@SequenceGenerator(name = "MSI_APPOINTMENT_SEQ1_GEN", sequenceName = "MSI_APPOINTMENT_SEQ1", allocationSize = 1)
	private int id;
	@Column
	private String time;
	
	@Column
	private String previous_diagnosis;
	
	@Column
	private String reason;
	
	@Column
	private String note;
	
	@Column
	private String status;
	
	@Column
	private String prescription;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JsonSerialize(using = CustomUserSerializer.class)
	@JoinTable(name = "msi_user_appointment", joinColumns = {
			@JoinColumn(name = "appointment_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "user_id", referencedColumnName = "id") })
	private List<User> users = new ArrayList<User>();
	
	
	
	
	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPrevious_diagnosis() {
		return previous_diagnosis;
	}

	public void setPrevious_diagnosis(String previous_diagnosis) {
		this.previous_diagnosis = previous_diagnosis;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", time=" + time + ", previous_diagnosis=" + previous_diagnosis + ", reason="
				+ reason + ", note=" + note + ", status=" + status + ", prescription=" + prescription + "]";
	}

	

	
	
	
}
