package com.mercury.SpringBootRESTDemo.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.junit4.SpringRunner;

import com.mercury.SpringBootRESTDemo.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableCaching
public class CacheTests {
	
	@Autowired
	OrderService orderService;
	
	@Test
	public void testSlowFunction() {
		System.out.println(orderService.slowTest("GREGORY"));
		System.out.println(orderService.slowTest("ROBERT"));

		System.out.println(orderService.slowTest("GREGORY"));
		System.out.println(orderService.slowTest("ROBERT"));
	}
}
