package com.mercury.core.bean;

import java.util.Date;
import java.util.Map;

public class OrderMessage {

	private int user_id;
	private int order_id;
	private Date purchase_date;
	/*
	 * <Product Id, Product Count>
	 */
	private Map<Integer, Integer> products;

	public OrderMessage() {
		super();
	}

	public OrderMessage(int user_id, int order_id, Date purchase_date, Map<Integer, Integer> products) {
		super();
		this.user_id = user_id;
		this.order_id = order_id;
		this.purchase_date = purchase_date;
		this.products = products;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public Date getPurchase_date() {
		return purchase_date;
	}

	public void setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}

	public Map<Integer, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<Integer, Integer> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "OrderMessage [user_id=" + user_id + ", order_id=" + order_id + ", purchase_date=" + purchase_date
				+ ", products=" + products + "]";
	}

}
