package com.example.sbweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LogOutController {
	  @GetMapping("/logout")
	    public String logout(HttpServletRequest request, HttpServletResponse response) {
	        // Invalidate user session or perform logout logic
	        request.getSession().invalidate();
	        
	        // Redirect to the home page or any other page after logout
	        return "index";
	    }

}
