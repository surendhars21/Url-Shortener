package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.Url;

public interface UrlRepository extends JpaRepository<Url, Integer>{
    @Query("select u.url from User u where u.id=?1")
	public List<Url> findByUserId(int id);
}
