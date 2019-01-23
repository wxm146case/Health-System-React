//package com.mercury.SpringBootRESTDemo.jms;
//
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.Session;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.core.MessageCreator;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.stereotype.Component;
//
//@Component
//public class EmailProducer {
//
//	@Autowired
//	private JmsTemplate jmsQueueTemplate;
//	
//
//	public void send(String to, String subject, String text) {
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setTo(to);
//		message.setSubject(subject);
//		message.setText(text);
//		jmsQueueTemplate.send("emailQueue",  new MessageCreator() {
//
//			@Override
//			public Message createMessage(Session session) throws JMSException {
//				return session.createObjectMessage(message);
//			}
//			
//		});
//	}
//
//}
