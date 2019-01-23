package com.mercury.SpringBootRESTDemo.bean;

public class ResponseAppointment {
	private int id;
	private String time;
	private String previous_diagnosis;
	private String reason;
	private String note;
	private String status;
	private String doctor_name;
	private String patient_name;
	private String prescription;
	private int doctor_id;
	
	
	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	public int getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	public String getDoctor_name() {
		return doctor_name;
	}
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	@Override
	public String toString() {
		return "ResponseAppointment [id=" + id + ", time=" + time + ", previous_diagnosis=" + previous_diagnosis
				+ ", reason=" + reason + ", note=" + note + ", status=" + status + ", doctor_name=" + doctor_name
				+ ", patient_name=" + patient_name + ", doctor_id=" + doctor_id + "]";
	}
	
	
	
	

	
	
}
