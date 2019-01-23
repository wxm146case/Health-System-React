package com.mercury.SpringBootRESTDemo.jms;

import java.io.Serializable;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.mercury.SpringBootRESTDemo.bean.Order;
import com.mercury.core.bean.OrdersReport;

@Component
public class OrdersReportProducer {

	@Autowired
	private JmsTemplate jmsQueueTemplate;

	public void sendOrdersForReport(Authentication authentication, List<Order> orders) {
		OrdersReport ordersReport = new OrdersReport();
		ordersReport.setUsername(authentication.getName());
		ordersReport.setTotal(orders.size());
		ordersReport.setTotalAmount(1000); // TODO: hard code for now

		jmsQueueTemplate.send("ordersReportQueue", new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage((Serializable)ordersReport);
			}

		});
	}

}
