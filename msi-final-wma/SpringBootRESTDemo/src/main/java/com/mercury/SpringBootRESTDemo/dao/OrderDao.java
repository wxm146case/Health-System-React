package com.mercury.SpringBootRESTDemo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mercury.SpringBootRESTDemo.bean.Order;

public interface OrderDao extends CrudRepository<Order, Integer>{

	public List<Order> findByUserId(int id);
}
