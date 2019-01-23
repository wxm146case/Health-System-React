package com.mercury.SpringBootRESTDemo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.SpringBootRESTDemo.bean.Order;
import com.mercury.SpringBootRESTDemo.bean.OrderProduct;
import com.mercury.SpringBootRESTDemo.bean.Product;
import com.mercury.SpringBootRESTDemo.dao.OrderDao;
import com.mercury.SpringBootRESTDemo.dao.OrderProductDao;
import com.mercury.SpringBootRESTDemo.dao.ProductDao;
import com.mercury.SpringBootRESTDemo.dao.UserDao;
import com.mercury.SpringBootRESTDemo.http.Response;
import com.mercury.SpringBootRESTDemo.kafka.OrderProcessService;
import com.mercury.SpringBootRESTDemo.security.SecurityUtils;

@Service
@Transactional
public class OrderService {

	@Autowired
	OrderDao orderDao;

	@Autowired
	ProductDao productDao;
	
	@Autowired
	OrderProductDao orderProductDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	OrderProcessService orderProcessService;

	public Response addOrder(Order order, Authentication authentication) {
		try {
			List<OrderProduct> purchases = order.getPurchases();
			purchases.forEach((orderProduct) -> {
				Product product = (Product) productDao.findById(orderProduct.getProduct().getId()).get();
				orderProduct.setProduct(product);
				orderProduct.setOrder(order);
			});
			order.setUser(userDao.findByUsername(authentication.getName()));
			order.setPurchases(null);
			orderDao.save(order);
			// TODO: fix cascade, onetomany, auto id bug.
			purchases.forEach((orderProduct) -> {
				orderProductDao.save(orderProduct);
			});
			// send to kafka for further processing
			order.setPurchases(purchases);
			orderProcessService.processOrder(order);
			return new Response(true);
		} catch (Exception e) {
			return new Response(false);
		}
	}

	public Response editOrder(Order order) {
		try {
			Order o = (Order) orderDao.findById(order.getId()).get();
			List<OrderProduct> purchasesToRemove = o.getPurchases();
			
			List<OrderProduct> purchases = order.getPurchases();
			// handled update and add products in order
			purchases.forEach((orderProduct) -> {
				Product product = (Product) productDao.findById(orderProduct.getProduct().getId()).get();
				orderProduct.setProduct(product);
				orderProduct.setOrder(o);
			});
			
			// handle remove products in order
			if(purchases.size() > 0) {
				purchasesToRemove = purchasesToRemove.stream().filter((orderProduct) -> {
					return !purchases.contains(orderProduct);
				}).collect(Collectors.toList());
			}
			
			o.setPurchases(purchases);
			orderDao.save(o);
			
			deleteOrderProducts(purchasesToRemove);	
						
			return new Response(true);
		} catch (Exception e) {
			System.out.println(e);
			return new Response(false);
		}
	}
	
	// TODO: move to OrderProductService
	public void deleteOrderProducts(List<OrderProduct> purchases) {
		orderProductDao.deleteAll(purchases);	
	}
	
	public List<Order> getOrders(Authentication authentication) {
		if(SecurityUtils.isAdmin(authentication.getAuthorities())) {
			return (List<Order>)orderDao.findAll();
		} else {
			return orderDao.findByUserId(userDao.findByUsername(authentication.getName()).getId());
		}
	}
	
	@Cacheable("slowTestResults")
	public String slowTest(String x) {
		try {
			Thread.sleep(3000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new String(x.toLowerCase());
	}

}
