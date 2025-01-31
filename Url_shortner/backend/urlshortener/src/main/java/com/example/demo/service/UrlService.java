package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UrlDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.ResponseStructure;
import com.example.demo.dto.Url;
import com.example.demo.dto.User;

@Service
public class UrlService {

	@Autowired
	private UrlDao urlDao;
	@Autowired
	private UserDao userDao;
	
	public ResponseStructure<Url> saveUrl(Url url,int user_id) {
		ResponseStructure<Url> structure=new ResponseStructure<>();
		Optional<User> recUser=userDao.findById(user_id);
		if(recUser.isPresent()) {
			User userdb=recUser.get();
			userdb.getUrl().add(url);
			url.setUser(userdb);
			structure.setData(urlDao.saveUrl(url));
			structure.setMessage("Url saved successfullyy......");
			structure.setStatuscode(HttpStatus.CREATED.value());
			return structure;
		}
		structure.setMessage("Invalid user id......");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return structure;
	}
	
	public ResponseStructure<Url> updateUrl(Url url){
	ResponseStructure<Url> structure=new ResponseStructure<>();
	Optional<Url> recUrl=urlDao.findById(url.getId());
	if(recUrl.isPresent()) {
		Url urldb=recUrl.get();
		urldb.setNewurl(url.getNewurl());
		urldb.setOldur(url.getOldur());
	    urldb.setCount(url.getCount());
	    structure.setData(urlDao.saveUrl(urldb));
	    structure.setMessage("Updated successfully......");
	    structure.setStatuscode(HttpStatus.ACCEPTED.value());
	    return structure;
	}
	structure.setMessage("Invalid id......");
    structure.setStatuscode(HttpStatus.NOT_FOUND.value());
    return structure;
	
	}
	
	public ResponseStructure<Url> findById(int id){
		ResponseStructure<Url> structure=new ResponseStructure<>();
		Optional<Url> recUrl=urlDao.findById(id);
		if(recUrl.isPresent()) {
			structure.setData(recUrl.get());
			structure.setMessage("Url found..........");
			structure.setStatuscode(HttpStatus.OK.value());
			return structure;
		}
		structure.setMessage("Invalid url id..........");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return structure;
	}
	
	public ResponseStructure<List<Url>> findByUserId(int id){
		ResponseStructure<List<Url>> structure=new ResponseStructure<>();
		List<Url> url=urlDao.findByUserId(id);
		if(url.size()>0) {
			structure.setData(url);
			structure.setMessage("List pf all url in the user id.....");
			structure.setStatuscode(HttpStatus.OK.value());
			return structure;
		}
		structure.setMessage("Invalid user id.....");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return structure;
	}
	
	public ResponseStructure<List<Url>> findAll(){
		ResponseStructure<List<Url>> structure=new ResponseStructure<>();
		List<Url> url=urlDao.findAll();
		if(url.size()>0) {
			structure.setData(url);
			structure.setMessage("List of all url .....");
			structure.setStatuscode(HttpStatus.OK.value());
			return structure;
		}
		structure.setMessage("NO data in your database .....");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return structure;
	}
}





