package com.mercury.SpringBootRESTDemo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.SpringBootRESTDemo.bean.Order;
import com.mercury.SpringBootRESTDemo.dao.OrderDao;
import com.mercury.SpringBootRESTDemo.http.Response;
import com.mercury.SpringBootRESTDemo.jms.OrdersReportProducer;
import com.mercury.SpringBootRESTDemo.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrdersReportProducer ordersReportProducer;

	@GetMapping("/orders")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER', 'ROLE_PATIENT', 'ROLE_DOCTOR')")
	public List<Order> getOrders(Authentication authentication) {
		System.out.println("GetOrders is invoking!");
		return orderService.getOrders(authentication);
	}
	
	@GetMapping("/orders/{id}")
	public Order getOrder(@PathVariable int id) {
		return orderDao.findById(id).get();
	}
	
	public void printOrders() {
		System.out.println(Arrays.asList((List<Order>) orderDao.findAll()));
	}
	
	@PostMapping("/orders")
	public Response postOrders(@RequestBody Order order, Authentication authentication) {
		return orderService.addOrder(order, authentication);
	}
	
	@PutMapping("/orders")
	public Response putOrders(@RequestBody Order order) {
		return orderService.editOrder(order);
	}
	
	@GetMapping("/orders_report")
	public void getOrdersReport(Authentication authentication) {
		ordersReportProducer.sendOrdersForReport(authentication, getOrders(authentication));
	}
	
}
