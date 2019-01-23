package com.mercury.SpringBootRESTDemo.kafka;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.mercury.SpringBootRESTDemo.bean.Order;
import com.mercury.core.bean.OrderMessage;

@Service
public class OrderProcessService {

	private KafkaTemplate<String, OrderMessage> kafkaTemplate;

	@Autowired
	 public OrderProcessService(KafkaTemplate<String, OrderMessage> kafkaTemplate) {
	    this.kafkaTemplate = kafkaTemplate;
	}
	
	public void processOrder(Order order) {
		OrderMessage orderMessage = new OrderMessage();
		orderMessage.setUser_id(order.getUser().getId());
		orderMessage.setOrder_id(order.getId());
		orderMessage.setPurchase_date(order.getPurchase_date());
		Map<Integer, Integer> products = new HashMap<>();
		order.getPurchases().forEach(purchase -> {
			products.put(purchase.getProduct().getId(), purchase.getQty());
		});
		orderMessage.setProducts(products);
		kafkaTemplate.send("orders", orderMessage);
	}

}
