package com.aws.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aws.api.model.AppResponse;
import com.aws.api.model.User;
import com.aws.api.service.DynamoService;

/**
 * 
 * @author Mohanlal
 *
 */
@RestController
@RequestMapping("/sample-dynamo-application")
public class DynamoController {

	@Autowired
	DynamoService service;
	
	@PostMapping("/user")
	public ResponseEntity<AppResponse<String>> saveUser(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.saveUser(user));
	}
	
	@GetMapping("/user")
	public ResponseEntity<AppResponse<User>> getUserByEmail(@RequestParam String emailId){
		return ResponseEntity.status(HttpStatus.OK).body(service.getUserByEmail(emailId));
	}
	
	@PatchMapping("/user")
	public ResponseEntity<AppResponse<String>> updateUser(@RequestParam String id,@RequestBody User user){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.updateUser(id,user));
	}
	
	@DeleteMapping("/user")
	public ResponseEntity<AppResponse<String>> removeUser(@RequestParam String id){
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.removeUser(id));
	}
	
}
