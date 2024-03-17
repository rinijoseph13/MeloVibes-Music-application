package com.example.sbweb.repositories;

import com.example.sbweb.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository <Users,Integer>
{
	public Users findByEmail(String email);
	//public  Users findByName(String username);
	

}
