package com.example.sbweb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sbweb.entities.Users;
import com.example.sbweb.repositories.UsersRepository;

@Service
public class UsersServiceImplementation implements UsersService {
	@Autowired
	UsersRepository repo;

	@Override
	public String addUser(Users user) {
		repo.save(user);
		return "User is created and saved";
	}

	@Override
	public boolean emailExists(String email) {
		if(repo.findByEmail(email)==null)
		{
			return false;
		}
		else
		{
			return true;
		}
		
	}

	@Override
	public boolean validateUser(String email, String password) {
		Users user=repo.findByEmail(email);
		String db_password=user.getPassword();
		if(db_password.equals(password))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public String getRole(String email) {
		return(repo.findByEmail(email).getRole());
		
	}

	@Override
	public Users getUser(String email) {
		Users user =repo.findByEmail(email);
		return user;
	}

	@Override
	public String updateUser(Users user) {
		repo.save(user);
		return "User is created and saved";
	}
	
	

}
