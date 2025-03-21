package com.shreyas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shreyas.payloads.WeatherResponse;
import com.shreyas.services.ExternalServiceImpl;

@RestController
@RequestMapping("/external")
public class ExternalApiController {

	@Autowired
	private ExternalServiceImpl service;
	@GetMapping("/find-weather")
	public ResponseEntity<WeatherResponse> findWeather(@RequestParam String city){
		return ResponseEntity.ok(service.hitApi(city));
	}
}
