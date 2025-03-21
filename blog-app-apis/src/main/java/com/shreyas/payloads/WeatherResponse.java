package com.shreyas.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class WeatherResponse {

	// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
	// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
	/*
	 * ObjectMapper om = new ObjectMapper(); Root root = om.readValue(myJsonString,
	 * Root.class);
	 */

	private Current current;
	

	@Getter
	@Setter
	public class Current {
		@JsonProperty("observation_time")
		private String observationTime;
		private int temperature;
		@JsonProperty("wind_speed")
		private int windSpeed;
		private int pressure;
		private int precip;
		private int humidity;
		private int cloudcover;
		private int feelslike;
		private int visibility;
	}

}
