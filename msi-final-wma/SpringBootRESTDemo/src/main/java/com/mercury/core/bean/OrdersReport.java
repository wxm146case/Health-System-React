package com.mercury.core.bean;

import java.io.Serializable;

public class OrdersReport implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private int total;
	private double totalAmount;

	public OrdersReport() {
		super();
	}

	public OrdersReport(String username, int total, double totalAmount) {
		super();
		this.username = username;
		this.total = total;
		this.totalAmount = totalAmount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
