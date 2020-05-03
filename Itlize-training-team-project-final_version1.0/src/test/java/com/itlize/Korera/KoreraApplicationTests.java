package com.itlize.Korera;

import com.itlize.Korera.dbModels.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class KoreraApplicationTests {
	@Autowired
	User user;

	@Test
	void contextLoads() {
		System.out.println(user);
	}

 }
