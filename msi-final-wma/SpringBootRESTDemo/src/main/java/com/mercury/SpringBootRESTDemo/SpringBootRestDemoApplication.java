package com.mercury.SpringBootRESTDemo;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
@EnableCaching
public class SpringBootRestDemoApplication implements CommandLineRunner {
	
//	@Autowired
//	ProductDao productDao;
	
//	@Autowired
//	DummyDao dummyDao;
	
//	@Autowired
//	OrderController orderController;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestDemoApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
//		System.out.println(Arrays.asList(productDao.findAll()));
//		
//		IDynamicDao dynamicDao = (IDynamicDao)dummyDao;
//		dynamicDao.sayHello();
//		
//		System.out.println(Arrays.asList(productDao.findByBrand("Staples")));
//		System.out.println(productDao.getMaxPrice());
//		System.out.println(productDao.getProducstWithStock(100));
//		
//		orderController.printOrders();
	}
	
	// JMS
	@Value("${jms.broker-url}")
	private String jmsBrokerUrl;
	
	@Value("${jms.user}")
	private String user;
	
	@Value("${jms.password}")
	private String password;
	
	@Bean
	public ConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL(jmsBrokerUrl);
		factory.setUserName(user);
		factory.setPassword(password);
		return factory;
	}
	
	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
		jmsTemplate.setDefaultDestinationName("emailQueue");
		return jmsTemplate;
	}
}
