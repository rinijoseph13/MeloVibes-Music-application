package com.example.sbweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sbweb.entities.Songs;
import com.example.sbweb.entities.Users;
import com.example.sbweb.services.SongsService;
import com.example.sbweb.services.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
	@Autowired
	UsersService usersv;
	@Autowired
	SongsService sserv;
	@PostMapping("/register")
	public String addUser(@ModelAttribute Users user)
	{
		boolean userstatus =usersv.emailExists(user.getEmail());
		if(userstatus==false)
		{
			usersv.addUser(user);
			return "login";
		}
		else
		{
		   return "login";
		}
	}
	@PostMapping("/login")
	public String validateUser(@RequestParam String email,@RequestParam String password,HttpSession session)
	{
		
		if( usersv.validateUser(email,password)== true)
		{
			session.setAttribute("email", email);
			if(usersv.getRole(email).equals("admin"))
			{
				return "adminhome";
			}
			else
			{
				return "customerhome";
			}
		}
		else
		{
			return "register";
		}
	}
	
	@GetMapping("/exploresongs")
	public String exploreSongs(HttpSession session,Model model)
	{
		String email=(String) session.getAttribute("email");
		Users user=usersv.getUser(email);
		boolean userStatus=user.isPremium();
				
		if(userStatus==true) {
			
		return "customer";
	}
		else
		{
			return "makepayment";
		}
	}
	

}
