package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseStructure;
import com.example.demo.dto.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserController {
 
	@Autowired
	private UserService service;
	
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public ResponseStructure<User> saveUser(@RequestBody User user) {
		return service.saveUser(user);
	}
	
	@PutMapping
	public ResponseStructure<User> updateUser(@RequestBody User user){
		return service.updateUser(user);
	}
	
	@GetMapping("/{id}")
	public ResponseStructure<User> findById(@PathVariable(name="id") int id){
		return service.findById(id);
	}
	
	@GetMapping("/verify-phone")
	public ResponseStructure<User> verifyPhone(@RequestParam(name="phone") long phone,@RequestParam(name="password") String password){
		return service.verifyPhone(phone, password);
	}
	
	@GetMapping("/verify-email")
	public ResponseStructure<User> verifyEmail(@RequestParam(name="email") String email,@RequestParam(name="password") String password){
		return service.verifyEmail(email, password);
	}
	
	@GetMapping
	public ResponseStructure<List<User>> findAll(){
		return service.findAll();
	}
}
