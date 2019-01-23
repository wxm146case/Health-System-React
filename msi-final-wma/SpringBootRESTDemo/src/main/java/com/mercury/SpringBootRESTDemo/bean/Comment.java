package com.mercury.SpringBootRESTDemo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "msi_comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MSI_COMMENT_SEQ1_GEN")
	@SequenceGenerator(name = "MSI_COMMENT_SEQ1_GEN", sequenceName = "MSI_COMMENT_SEQ1", allocationSize = 1)
	private int id;
	
	@Column
	private String patient_name;
	
	@Column
	private int doctor_id;
	
	@Column
	private int point;
	
	@Column
	private String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}


	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", patient_name=" + patient_name + ", doctor_id=" + doctor_id + ", point=" + point
				+ ", content=" + content + "]";
	}

	
	
	
	
}
