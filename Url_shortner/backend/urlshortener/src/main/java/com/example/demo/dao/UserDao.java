package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.example.demo.Repository.UserRepository;
import com.example.demo.dto.ResponseStructure;
import com.example.demo.dto.User;

@Repository
public class UserDao {

	@Autowired
	private UserRepository repository;
	
	public User saveUser(User user) {
		return repository.save(user);
	}
	public Optional<User> findById(int id) {
		return repository.findById(id);
	}
	
	public Optional<User> verifyPhone(long phone,String password){
		return repository.verifyPhone(phone, password);
	}
	public Optional<User> verifyEmail(String email,String password){
		return repository.verifyEmail(email, password);
	}
	public List<User> findAll(){
		return repository.findAll();
	}
}
