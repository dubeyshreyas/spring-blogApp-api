package com.shreyas.services;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RedisService {

	@Autowired
	private RedisTemplate redisTemplate;
	public <T> T getCache(String key, Class<T> entityClass) {
		try {
		Object o = redisTemplate.opsForValue().get(key);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(o.toString(), entityClass);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			return null;
		}
		
	}
	
	public void setCache(String key, Object o, Long ttl) {
		try {
		   ObjectMapper mapper = new ObjectMapper();
		   String json = mapper.writeValueAsString(o);
		   redisTemplate.opsForValue().set(key,json,ttl,TimeUnit.SECONDS);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}
		
	}
}
