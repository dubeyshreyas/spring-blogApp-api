package com.shreyas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import com.shreyas.repo.UserRepo;

@SpringBootTest
class BlogAppApisApplicationTests {
	
	@Autowired
	UserRepo repo;
	
	@Autowired
	private RedisTemplate redisTemplate;
	

	@Test
	@Disabled
	void contextLoads() {
	}
	
	@Disabled
	@ParameterizedTest
	@CsvSource({
		"1,1,1",
		"1,2,3",
		"10,12,22"
		
	})
	void test(int a, int b, int expected) {
		assertEquals(expected, a+b);
		
	}
	
	@Disabled
	@Test
	void testgetAll() {
		assertTrue(!repo.findAll().isEmpty());
	}
	
	@Test
	void redisTest() {
		redisTemplate.opsForValue().set("Salary", "1234");
		System.out.println("checking redis");
		System.out.println(redisTemplate.opsForValue().get("Salary"));
	}

}
