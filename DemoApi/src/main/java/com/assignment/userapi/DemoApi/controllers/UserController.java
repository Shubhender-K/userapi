package com.assignment.userapi.DemoApi.controllers;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.assignment.userapi.DemoApi.payloads.User;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private RestTemplate restTemplate;
		
	@GetMapping("/get")
	public ResponseEntity<List<User>> getUsers(){
	
		ResponseEntity<List<User>> listOfUsers =
		        restTemplate.exchange("https://jsonplaceholder.typicode.com/users",
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
		            });
		return listOfUsers;
	}
	
	@GetMapping("/search/{username}")
	public ResponseEntity<List<User>> searchByUserName(@PathVariable("username") String username ){
		ResponseEntity<List<User>> listOfUsers =
		        restTemplate.exchange("https://jsonplaceholder.typicode.com/users",
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
		            });
		
		ArrayList<User> arr= (ArrayList<User>) listOfUsers.getBody();
		
		
		List<User> collect = arr.stream().filter(str -> str.getUsername().equals(username)).collect(Collectors.toList());
		
		return new ResponseEntity<List<User>>(collect,HttpStatus.OK);
		
	}
	
	
	
	@RequestMapping("/create")
	public String createUser(@RequestBody User user){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<User>(user, httpHeaders);
        return restTemplate.exchange("https://jsonplaceholder.typicode.com/users", HttpMethod.POST, entity, String.class).getBody();
	}
	
	
	@RequestMapping(value = "/update/{id}")
    public String updatePost(@PathVariable("id") int id, @RequestBody User user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user, httpHeaders);
        return restTemplate.exchange("https://jsonplaceholder.typicode.com/users/" + id, HttpMethod.PUT, entity, String.class).getBody();
    }
	
	
	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange("https://jsonplaceholder.typicode.com/users/" + id, HttpMethod.DELETE, entity, String.class).getBody();
    }
}
	
