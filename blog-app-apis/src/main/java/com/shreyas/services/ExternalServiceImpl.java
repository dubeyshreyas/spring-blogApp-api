package com.shreyas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.shreyas.payloads.WeatherResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ExternalServiceImpl {

	private String CITY = "New Delhi";
    private String url = "https://api.weatherstack.com/current?access_key=2046e183e95f1c34d6bef955f29b91da&query=name";
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RedisService redisService;
	
	
	public WeatherResponse hitApi(String name) {
		WeatherResponse weatherResponse = redisService.getCache(name, WeatherResponse.class);
		if(weatherResponse != null) {
			System.out.println("redis called");
			return weatherResponse;
		}
		else {
			
		if(!name.isBlank()) this.CITY = name;
		String URL = url.replace("name", CITY);
		ResponseEntity<WeatherResponse> response =  serviceResponse(URL);
		//ResponseEntity<String> response =  restTemplate.exchange(URL, HttpMethod.GET, null, String.class);
		System.out.println(response);
		//System.out.println(response.getBody());
		WeatherResponse body = response.getBody();
		redisService.setCache(name, body, 100l);
		return body;}
		
		//return new WeatherResponse();
	}
	
	public ResponseEntity<WeatherResponse> serviceResponse(String URL) {
		 try {
		        ResponseEntity<WeatherResponse> response = restTemplate.exchange(URL, HttpMethod.GET, null, WeatherResponse.class);
		        
		        if (!response.getStatusCode().is2xxSuccessful()) {
		            throw new RuntimeException("Non-200 response received: " + response.getStatusCode());
		        }
		        
		        return response;
		    } catch (Exception e) {
		        throw new RuntimeException("API call failed: " + e.getMessage(), e);
		    }
	}
	
	
}
