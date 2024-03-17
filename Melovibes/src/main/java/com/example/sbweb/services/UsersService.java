package com.example.sbweb.services;
import com.example.sbweb.entities.Users;
public interface UsersService {
	public String addUser(Users user);
	public boolean emailExists(String email);
	public boolean validateUser(String email,String password);
	public String getRole(String email);
	public Users getUser(String email);
	public String updateUser(Users user);

}
