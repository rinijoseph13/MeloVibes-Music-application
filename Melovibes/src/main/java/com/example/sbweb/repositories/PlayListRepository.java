package com.example.sbweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.example.sbweb.entities.PlayList;

public interface PlayListRepository extends JpaRepository<PlayList,Integer>{
	
	 

	public List<PlayList> findByNameContainingIgnoreCase(String name);
}
