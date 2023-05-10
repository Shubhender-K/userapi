package com.assignment.userapi.DemoApi.payloads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
	
	private String street;
	
	private String suite;
	
	private String city;
	
	private String zipcode;
	
	private Geo geo;
	
	
	

}
